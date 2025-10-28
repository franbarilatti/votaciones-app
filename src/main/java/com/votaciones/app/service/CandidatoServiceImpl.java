package com.votaciones.app.service;

import com.votaciones.app.exception.ResourceNotFoundException;
import com.votaciones.app.model.Candidato;
import com.votaciones.app.repository.CandidatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoServiceImpl implements CandidatoService{

    private final CandidatoRepository candidatoRepository;

    public CandidatoServiceImpl(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    @Override
    public Candidato create(Candidato candidato){
        return candidatoRepository.save(candidato);
    }

    @Override
    public List<Candidato> findAll(){
        return candidatoRepository.findAll();
    }

    @Override
    public Candidato findById(Long id){
        return candidatoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Candidato no encontrado con id: " + id));
    }

    @Override
    public void delete(Long id){
        Candidato candidato = findById(id);
        candidatoRepository.delete(candidato);
    }



}
