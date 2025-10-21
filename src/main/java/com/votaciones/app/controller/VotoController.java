package com.votaciones.app.controller;

import com.votaciones.app.dto.VotoDto;
import com.votaciones.app.model.Voto;
import com.votaciones.app.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/votos")
@Tag(name = "Votos", description = "Registrar y consultar votos")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @Operation(summary = "Registrar voto")
    @PostMapping
    public ResponseEntity<Map<String,Object>> vote(@RequestBody @Valid VotoDto dto){
        Voto v = votoService.registerVote(dto.getCandidatoId());
        Map<String,Object> resp = new HashMap<>();
        resp.put("id", v.getId());
        resp.put("candidatoId", v.getCandidato().getId());
        resp.put("fechaEmision", v.getFechaEmision());
        return ResponseEntity.status(201).body(resp);
    }

    @Operation(summary = "Contrar votos por candidato")
    @GetMapping("/por-candidato/{id}")
    public ResponseEntity<Map<String,Object>> countByCandidato(@PathVariable Long id){
        long total = votoService.countVotesByCandidato(id);
        Map<String,Object> resp = new HashMap<>();
        resp.put("candidatoId", id);
        resp.put("votos", total);
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Contar votos por partido")
    @GetMapping("/por-partido/{id}")
    public ResponseEntity<Map<String,Object>> countByPartido(@PathVariable Long id) {
        long total = votoService.countVotesByPartido(id);
        Map<String,Object> resp = new HashMap<>();
        resp.put("partidoId", id);
        resp.put("votos", total);
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Estadísticas: votos por partido")
    @GetMapping("/estadisticas/por-partido")
    public ResponseEntity<List<Object[]>> statsByPartido() {
        return ResponseEntity.ok(votoService.votesForPartido());
    }









}
