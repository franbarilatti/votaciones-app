package com.votaciones.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CandidatoDto {

    @NotBlank
    private String nombreCompleto;

    @NotBlank
    private Long partidoId;


}
