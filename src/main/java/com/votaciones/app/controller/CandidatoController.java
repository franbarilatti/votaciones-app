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
    public ResponseEntity<CandidatoDto> create(@Valid @RequestBody CandidatoDto dto){
        PartidoPolitico partidoPolitico = partidoPoliticoService.findById(dto.getPartidoId());
        Candidato candidato = new Candidato();
        candidato.setNombreCompleto(dto.getNombreCompleto());
        candidato.setPartido(partidoPolitico);

        Candidato creado = candidatoService.create(candidato);

        CandidatoDto resp = new CandidatoDto();
        resp.setId(creado.getId());
        resp.setNombreCompleto(creado.getNombreCompleto());
        resp.setPartidoNombre(partidoPolitico.getNombre());
        resp.setPartidoSigla(partidoPolitico.getSigla());

        return ResponseEntity.status(201).body(resp);
    }

    @Operation(summary = "Listar Candidatos")
    @GetMapping
    public ResponseEntity<List<CandidatoDto>> findAll(){
        List<Candidato> candidatos = candidatoService.findAll();

        List<CandidatoDto> dtos = candidatos.stream().map(c -> {
            CandidatoDto dto = new CandidatoDto();
            dto.setId(c.getId());
            dto.setNombreCompleto(c.getNombreCompleto());
            if(c.getPartido() != null){
                dto.setPartidoNombre(c.getPartido().getNombre());
                dto.setPartidoSigla(c.getPartido().getSigla());
            }
            return dto;
        }).toList();

        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Obtener candidato por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CandidatoDto> findById(@PathVariable Long id){
        Candidato candidato = candidatoService.findById(id);

        CandidatoDto resp = new CandidatoDto();
        resp.setId(candidato.getId());
        resp.setNombreCompleto(candidato.getNombreCompleto());
        resp.setPartidoNombre(candidato.getPartido().getNombre());
        resp.setPartidoSigla(candidato.getPartido().getSigla());


        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Eliminar Candidato")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        candidatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
