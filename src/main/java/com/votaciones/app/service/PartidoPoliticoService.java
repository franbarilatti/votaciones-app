package com.votaciones.app.service;

import com.votaciones.app.model.PartidoPolitico;

import java.util.List;

public interface PartidoPoliticoService {
    PartidoPolitico create(PartidoPolitico partidoPolitico);
    List<PartidoPolitico> findAll();
    PartidoPolitico findById(Long id);
    void delete(Long id);
}
