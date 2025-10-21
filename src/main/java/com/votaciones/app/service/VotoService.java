package com.votaciones.app.service;

import com.votaciones.app.model.Voto;

import java.util.List;

public interface VotoService {

    Voto registerVote(Long candidatoId);
    long countVotesByCandidato(Long partidoId);
    long countVotesByPartido(Long partidoId);
    List<Object[]> votesForPartido();

}
