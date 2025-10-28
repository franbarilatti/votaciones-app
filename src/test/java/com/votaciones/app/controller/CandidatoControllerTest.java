package com.votaciones.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votaciones.app.model.Candidato;
import com.votaciones.app.model.PartidoPolitico;
import com.votaciones.app.service.CandidatoService;
import com.votaciones.app.service.PartidoPoliticoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CandidatoController.class)
class CandidatoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CandidatoService candidatoService;

    @MockitoBean
    private PartidoPoliticoService partidoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_shouldReturn201() throws Exception {
        PartidoPolitico p = new PartidoPolitico("Demo","DM"); p.setId(1L);
        Candidato c = new Candidato(); c.setId(2L); c.setNombreCompleto("Juan"); c.setPartido(p);

        when(partidoService.findById(1L)).thenReturn(p);
        when(candidatoService.create(any(Candidato.class))).thenReturn(c);

        mockMvc.perform(post("/api/candidatos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreCompleto\":\"Juan\",\"partidoId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nombreCompleto").value("Juan"));

        verify(partidoService).findById(1L);
        verify(candidatoService).create(any());
    }

    @Test
    void findAll_shouldReturn200() throws Exception {
        PartidoPolitico p = new PartidoPolitico("X","X"); p.setId(1L);
        Candidato c = new Candidato(); c.setId(5L); c.setNombreCompleto("Ana"); c.setPartido(p);
        when(candidatoService.findAll()).thenReturn(List.of(c));

        mockMvc.perform(get("/api/candidatos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreCompleto").value("Ana"));
        verify(candidatoService).findAll();
    }
}
