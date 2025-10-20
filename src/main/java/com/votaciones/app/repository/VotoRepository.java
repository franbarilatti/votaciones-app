package com.votaciones.app.repository;


import com.votaciones.app.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    long countByCandidatoId(Long candidatoId);

    @Query("SELECT v.candidato.partido.id AS partidoId, COUNT(v) AS votos FROM Voto v GROUP BY v.candidato.partido.id")
    List<Object[]> countVotosGroupedByPartido();

}
