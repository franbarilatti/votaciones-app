package com.votaciones.app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partidos_politicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoPolitico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String nombre;

    @Column(nullable = false, unique = true, length = 10)
    private String sigla;

    public PartidoPolitico(String nombre, String sigla) {
        this.nombre = nombre;
        this.sigla = sigla;
    }
}
