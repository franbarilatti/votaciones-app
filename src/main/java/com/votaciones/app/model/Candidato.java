package com.votaciones.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidatos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partido_id", nullable = false)
    private PartidoPolitico partido;



}
