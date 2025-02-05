package com.perrut.gestao_eventos.modules.institution.repositories;

import com.perrut.gestao_eventos.modules.institution.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findByNameContainingIgnoreCase(String name);

    boolean existsByInstitutionId(Long id);

    @Query("SELECT e FROM event e WHERE e.finalDate < :currentDate AND e.active = true")
    List<EventEntity> findExpiredEvents(LocalDate currentDate);

    @Query("SELECT e FROM event e WHERE e.finalDate BETWEEN :currentDate AND :futureDate")
    List<EventEntity> findByFinalDateInPeriod(@Param("currentDate") LocalDate currentDate, @Param("futureDate") LocalDate futureDate);
}
