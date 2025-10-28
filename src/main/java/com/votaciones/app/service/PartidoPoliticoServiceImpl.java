package com.votaciones.app.service;

import com.votaciones.app.exception.ResourceNotFoundException;
import com.votaciones.app.model.PartidoPolitico;
import com.votaciones.app.repository.PartidoPoliticoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidoPoliticoServiceImpl implements PartidoPoliticoService{

    private final PartidoPoliticoRepository repository;


    public PartidoPoliticoServiceImpl(PartidoPoliticoRepository repository){
        this.repository = repository;
    }

    @Override
    public PartidoPolitico create(PartidoPolitico partidoPolitico){
        return repository.save(partidoPolitico);
    }

    @Override
    public List<PartidoPolitico> findAll(){
        return repository.findAll();
    }

    @Override
    public PartidoPolitico findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Partido Politico no encontrado. Id del partido: " + id));
    }

    @Override
    public void delete(Long id){
        PartidoPolitico partidoPolitico = findById(id);
        repository.delete(partidoPolitico);
    }

}
