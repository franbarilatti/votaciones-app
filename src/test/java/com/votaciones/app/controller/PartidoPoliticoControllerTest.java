package com.votaciones.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votaciones.app.model.PartidoPolitico;
import com.votaciones.app.service.PartidoPoliticoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PartidoPoliticoController.class)
public class PartidoPoliticoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartidoPoliticoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_shouldReturn201() throws Exception {
        PartidoPolitico p = new PartidoPolitico("Demo","DM");
        p.setId(1L);

        when(service.create(any(PartidoPolitico.class))).thenReturn(p);

        String body = objectMapper.writeValueAsString(p);
        mockMvc.perform(post("/api/partidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Demo\",\"sigla\":\"DM\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
        verify(service).create(any());
    }

    @Test
    void findAll_shouldReturn200() throws Exception {
        when(service.findAll()).thenReturn(List.of(new PartidoPolitico("A","A")));
        mockMvc.perform(get("/api/partidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("A"));
        verify(service).findAll();
    }
}
