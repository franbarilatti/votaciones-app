package com.votaciones.app.controller;

import com.votaciones.app.dto.CandidatoDto;
import com.votaciones.app.model.Candidato;
import com.votaciones.app.model.PartidoPolitico;
import com.votaciones.app.service.CandidatoService;
import com.votaciones.app.service.PartidoPoliticoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
@Tag(name = "Candidatos", description = "Gestion de Candidatos")
public class CandidatoController {

    private final CandidatoService candidatoService;
    private final PartidoPoliticoService partidoPoliticoService;

    public CandidatoController(CandidatoService candidatoService, PartidoPoliticoService partidoPoliticoService) {
        this.candidatoService = candidatoService;
        this.partidoPoliticoService = partidoPoliticoService;
    }

    @Operation(summary = "Crear Candidato")
    @PostMapping
    public ResponseEntity<Candidato> create(@Valid @RequestBody CandidatoDto dto){
        PartidoPolitico partidoPolitico = partidoPoliticoService.findById(dto.getPartidoId());
        Candidato candidato = new Candidato();
        candidato.setNombreCompleto(dto.getNombreCompleto());
        candidato.setPartido(partidoPolitico);
        return ResponseEntity.status(201).body(candidatoService.create(candidato));
    }

    @Operation(summary = "Listar Candidatos")
    @GetMapping
    public ResponseEntity<List<Candidato>> findAll(){
        return ResponseEntity.ok(candidatoService.findAll());
    }

    @Operation(summary = "Obtener candidato por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Candidato> findById(@PathVariable Long id){
        return ResponseEntity.ok(candidatoService.findById(id));
    }

    @Operation(summary = "Eliminar Candidato")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        candidatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
