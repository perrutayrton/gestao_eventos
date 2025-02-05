package com.perrut.gestao_eventos.modules.institution.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventDTO {

    @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long instituitionId;

    @Schema(example = "Nome do evento", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(example = "01/01/1900", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate initialDate;

    @Schema(example = "01/01/1900", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate finalDate;
}
