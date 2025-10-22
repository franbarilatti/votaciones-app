package com.votaciones.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.votaciones.app.model.Candidato;
import com.votaciones.app.model.Voto;
import com.votaciones.app.service.VotoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VotoController.class)
class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotoService votoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void vote_shouldReturn201_withBody() throws Exception {
        Candidato candidato = new Candidato(); candidato.setId(1L);
        Voto voto = new Voto(); voto.setId(10L); voto.setCandidato(candidato); voto.setFechaEmision(LocalDateTime.now());

        when(votoService.registerVote(1L)).thenReturn(voto);

        mockMvc.perform(post("/api/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"candidatoId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.candidatoId").value(1));

        verify(votoService).registerVote(1L);
    }
}