package com.perrut.gestao_eventos.modules.institution.services;

import com.perrut.gestao_eventos.exceptions.FinalDateLessThanInitialDateException;
import com.perrut.gestao_eventos.exceptions.InstitutionNotFoundException;
import com.perrut.gestao_eventos.exceptions.NotExistsEventsInPeriodException;
import com.perrut.gestao_eventos.modules.institution.dtos.CreateEventDTO;
import com.perrut.gestao_eventos.modules.institution.dtos.EventDTO;
import com.perrut.gestao_eventos.modules.institution.entities.InstitutionEntity;
import com.perrut.gestao_eventos.modules.institution.entities.EventEntity;
import com.perrut.gestao_eventos.modules.institution.repositories.InstitutionRepository;
import com.perrut.gestao_eventos.modules.institution.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    public EventEntity createEvent(CreateEventDTO createEventDTO) {

        validateEvent(createEventDTO.getInstituitionId(),
                createEventDTO.getInitialDate(),
                createEventDTO.getFinalDate());


        EventEntity event = new EventEntity();
        event.setInstitutionId(createEventDTO.getInstituitionId());
        event.setName(createEventDTO.getName());
        event.setInitialDate(createEventDTO.getInitialDate());
        event.setFinalDate(createEventDTO.getFinalDate());
        event.setActive(isEventActive(createEventDTO.getFinalDate()));

       return eventRepository.save(event);
    }

    public List<EventEntity> getAll() {

        List<EventEntity> eventsList = eventRepository.findAll();

        if (eventsList.isEmpty()) {
            throw new RuntimeException("List empty");
        }

        return eventsList;
    }

    public List<EventDTO> getByEventsFilter(String filter) throws NameNotFoundException {

        List<EventEntity> events = eventRepository.findByNameContainingIgnoreCase(filter);
        return events.stream()
                .map(event -> EventDTO.builder()
                        .name(event.getName())
                        .instituitionId(event.getInstitutionId())
                        .initialDate(event.getInitialDate())
                        .finalDate(event.getFinalDate())
                        .active(event.isActive())
                        .build())
                .toList();
    }

    public EventDTO updateEvent(Long idEvent, EventDTO eventDTO) {

        validateEvent(eventDTO.getInstituitionId(),
                eventDTO.getInitialDate(),
                eventDTO.getFinalDate());

        EventEntity eventUpdate = eventRepository.findById(idEvent)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + idEvent));

        eventUpdate.setName(eventDTO.getName());
        eventUpdate.setInstitutionId(eventDTO.getInstituitionId());
        eventUpdate.setInitialDate(eventDTO.getInitialDate());
        eventUpdate.setFinalDate(eventDTO.getFinalDate());
        eventUpdate.setActive(eventUpdate.isActive());

      eventRepository.save(eventUpdate);

        return eventDTO;
    }

    private void validateEvent(Long idInstitution, LocalDate initialDate, LocalDate finalDate) {

        Optional<InstitutionEntity> company = institutionRepository.findById(idInstitution);
        if (company.isEmpty()) {
            throw new InstitutionNotFoundException();
        }

        if (finalDate.compareTo(initialDate) < 0) {
            throw new FinalDateLessThanInitialDateException();
        }
    }

    public void deleteEventById(Long id) {

        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException("Event not found with ID: " + id);
        }

        eventRepository.deleteById(id);
    }

    public boolean isEventActive(LocalDate finalDate) {

        return finalDate.compareTo(LocalDate.now()) > 0;
    }

    public List<EventEntity> getEventsWithFinalDateInPeriod(int days) {

        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(days);
        List<EventEntity> eventsToBeDeactived = eventRepository.findByFinalDateInPeriod(LocalDate.now(), futureDate);
        if (eventsToBeDeactived.isEmpty()) {
            throw new NotExistsEventsInPeriodException();
        }

        return eventsToBeDeactived;
    }
}
