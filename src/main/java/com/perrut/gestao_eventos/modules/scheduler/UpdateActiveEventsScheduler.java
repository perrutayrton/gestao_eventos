package com.perrut.gestao_eventos.modules.scheduler;

import com.perrut.gestao_eventos.modules.institution.entities.EventEntity;
import com.perrut.gestao_eventos.modules.institution.repositories.EventRepository;
import com.perrut.gestao_eventos.modules.institution.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class UpdateActiveEventsScheduler {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Scheduled(cron = "0 */2 * * * *")
    public void checkActiveEvents() {
        List<EventEntity> events = eventRepository.findExpiredEvents(LocalDate.now());

        events.forEach(event -> {
                eventRepository.save(EventEntity.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .institutionId(event.getInstitutionId())
                        .initialDate(event.getInitialDate())
                        .finalDate(event.getFinalDate())
                        .active(false)
                        .createdAt(event.getCreatedAt())
                        .build());
        });
    }
}
