package com.votaciones.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "votos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidato_id", nullable = false)
    private Candidato candidato;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    public Voto(Candidato candidato, LocalDateTime fechaEmision) {
        this.candidato = candidato;
        this.fechaEmision = fechaEmision;
    }

    public void prePersist(){
        if (fechaEmision == null){
            fechaEmision = LocalDateTime.now();
        }
    }


}
