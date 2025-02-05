package com.perrut.gestao_eventos.modules.institution.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionDTO {

    private Long id;

    @Schema(example = "Nome da instituição", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(example = "Tipo da instituição", requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;
}
