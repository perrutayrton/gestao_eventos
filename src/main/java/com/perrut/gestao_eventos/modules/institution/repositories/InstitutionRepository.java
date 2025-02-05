package com.perrut.gestao_eventos.modules.institution.repositories;

import com.perrut.gestao_eventos.modules.institution.entities.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Long> {

    Optional<InstitutionEntity> findByName(String name);


}
