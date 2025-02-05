package com.perrut.gestao_eventos.modules.institution.services;

import com.perrut.gestao_eventos.exceptions.*;
import com.perrut.gestao_eventos.modules.institution.dtos.CreateInstitutionDTO;
import com.perrut.gestao_eventos.modules.institution.dtos.InstitutionDTO;
import com.perrut.gestao_eventos.modules.institution.entities.InstitutionEntity;
import com.perrut.gestao_eventos.modules.institution.repositories.InstitutionRepository;
import com.perrut.gestao_eventos.modules.institution.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private EventRepository eventRepository;

    public InstitutionEntity createInstitution(CreateInstitutionDTO createInstitutionDTO) {

        this.institutionRepository
                .findByName(createInstitutionDTO.getName())
                .ifPresent((name) -> {
                    throw new NameFoundException();
                });

        return institutionRepository.save(InstitutionEntity.builder()
                .name(createInstitutionDTO.getName())
                .type(createInstitutionDTO.getType())
                .build());
    }

    public List<InstitutionEntity> getAll() {

        List<InstitutionEntity> companyList = institutionRepository.findAll();

        if (companyList.isEmpty()) {
            throw new RuntimeException("List empty");
        }

        return companyList;
    }

    public InstitutionDTO getByName(String name) throws NameNotFoundException {

    var institution = institutionRepository.findByName(name)
            .orElseThrow();

        return InstitutionDTO
                .builder()
                .id(institution.getId())
                .name(institution.getName())
                .type(institution.getType())
                .build();
    }

    public InstitutionDTO updateInstitution(Long idInstitution, InstitutionDTO institutionDTO) {

        InstitutionEntity institutionUpdate = institutionRepository.findById(idInstitution)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with ID: " + idInstitution));

        institutionUpdate.setName(institutionDTO.getName());
        institutionUpdate.setType(institutionDTO.getType());

        institutionRepository.save(institutionUpdate);

        return InstitutionDTO.builder()
                .id(institutionUpdate.getId())
                .name(institutionUpdate.getName())
                .type(institutionUpdate.getType())
                .build();
    }

    public Optional<InstitutionEntity> deleteInstitutionById(Long id) {

        if (!institutionRepository.existsById(id)) {
            throw new EntityNotFoundException("Company not found with ID: " + id);
        } else if (eventRepository.existsByInstitutionId(id)) {
            throw new EntityRelatedWithInstitutionException();
        }

        var companyDeleted = institutionRepository.findById(id);
        institutionRepository.deleteById(id);

        return companyDeleted;
    }
}
