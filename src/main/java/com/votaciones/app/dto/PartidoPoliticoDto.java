package com.votaciones.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoPoliticoDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String sigla;

}
