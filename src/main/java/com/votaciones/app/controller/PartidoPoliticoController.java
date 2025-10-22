package com.votaciones.app.controller;

import com.votaciones.app.dto.PartidoPoliticoDto;
import com.votaciones.app.model.PartidoPolitico;
import com.votaciones.app.service.PartidoPoliticoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@Tag(name = "Partidos Politicos", description = "Gestion de Partidos Politicos")
public class PartidoPoliticoController {

    private final PartidoPoliticoService partidoPoliticoService;

    public PartidoPoliticoController(PartidoPoliticoService partidoPoliticoService) {
        this.partidoPoliticoService = partidoPoliticoService;
    }

    @Operation(summary = "Crear Partido Politico")
    @PostMapping
    public ResponseEntity<PartidoPolitico> create(@Valid @RequestBody PartidoPoliticoDto dto){
        PartidoPolitico partidoPolitico = new PartidoPolitico();
        partidoPolitico.setSigla(dto.getSigla());
        partidoPolitico.setNombre(dto.getNombre());
        return ResponseEntity.status(201).body(partidoPoliticoService.create(partidoPolitico));
    }

    @Operation(summary = "Listar Partidos Politicos")
    @GetMapping
    public ResponseEntity<List<PartidoPolitico>> findAll(){
        return ResponseEntity.ok(partidoPoliticoService.findAll());
    }

    @Operation(summary = "Obtener Partido Politico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PartidoPolitico> findById(@PathVariable Long id){
        return ResponseEntity.ok(partidoPoliticoService.findById(id));
    }

    @Operation(summary = "Eliminar Partido Politico")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        partidoPoliticoService.delete(id);
        return ResponseEntity.noContent().build();
    }







}
