package com.votaciones.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CandidatoDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    private String nombreCompleto;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long partidoId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String partidoNombre;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String partidoSigla;


}
