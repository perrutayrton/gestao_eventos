package com.perrut.gestao_eventos.modules.institution.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(name = "event")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(name = "initial_date", nullable = false)
    private LocalDate initialDate;

    @Column(name = "final_date", nullable = false)
    private LocalDate finalDate;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "institution_id", insertable = false, updatable = false)
    private InstitutionEntity institutionEntity;

    @Column(name = "institution_id", nullable = false)
    private Long institutionId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotNull
    private boolean active;
}
