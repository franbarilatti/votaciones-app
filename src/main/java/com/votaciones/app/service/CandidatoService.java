package com.votaciones.app.service;


import com.votaciones.app.model.Candidato;

import java.util.List;

public interface CandidatoService {

Candidato create(Candidato candidato);
List<Candidato> findAll();
Candidato findById(Long id);
void delete(Long id);

}
