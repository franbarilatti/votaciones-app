package com.votaciones.app.service;

import com.votaciones.app.exception.ResourceNotFoundException;
import com.votaciones.app.model.Candidato;
import com.votaciones.app.model.Voto;
import com.votaciones.app.repository.CandidatoRepository;
import com.votaciones.app.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoServiceImpl implements VotoService{

    private final VotoRepository votoRepository;
    private final CandidatoRepository candidatoRepository;

    public VotoServiceImpl(VotoRepository votoRepository, CandidatoRepository candidatoRepository) {
        this.votoRepository = votoRepository;
        this.candidatoRepository = candidatoRepository;
    }

    @Override
    public Voto registerVote(Long candidatoId){
        Candidato candidato = candidatoRepository.findById(candidatoId).
                orElseThrow(() -> new ResourceNotFoundException("Candidato no encontrado con id: "+ candidatoId));
        Voto voto = new Voto();
        voto.setCandidato(candidato);
        return votoRepository.save(voto);
    }

    @Override
    public long countVotesByCandidato(Long candidatoId){
        return votoRepository.countByCandidatoId(candidatoId);
    }

    @Override
    public long countVotesByPartido(Long partidoId){
        List<Object[]> rows = votoRepository.countVotosGroupedByPartido();
        for(Object[] r : rows){
            Long pid = ((Number) r[0]).longValue();
            Long votos = ((Number) r[1]).longValue();
            if(pid.equals(partidoId)) return votos;
        }

        return 0L;
    }

    @Override
    public List<Object[]> votesForPartido(){
        return votoRepository.countVotosGroupedByPartido();
    }




}
