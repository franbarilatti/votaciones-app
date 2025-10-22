package com.votaciones.app.service;

import com.votaciones.app.exception.ResourceNotFoundException;
import com.votaciones.app.model.Candidato;
import com.votaciones.app.model.PartidoPolitico;
import com.votaciones.app.repository.CandidatoRepository;
import com.votaciones.app.repository.PartidoPoliticoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidatoServiceTest {
    @Mock
    private CandidatoRepository candidatoRepository;

    @Mock
    private PartidoPoliticoRepository partidoPoliticoRepository;

    @InjectMocks
    private CandidatoServiceImpl candidatoService;

    @Test
    void createWhenPartidoExistsShouldSave() {
        PartidoPolitico p = new PartidoPolitico("Demo","DM");
        p.setId(1L);
        Candidato c = new Candidato();
        c.setNombreCompleto("Juan");
        c.setPartido(p);

        when(partidoPoliticoRepository.findById(1L)).thenReturn(Optional.of(p));
        when(candidatoRepository.save(any(Candidato.class))).thenAnswer(inv -> {
            Candidato arg = inv.getArgument(0);
            arg.setId(2L);
            return arg;
        });

        Candidato saved = candidatoService.create(c);
        assertNotNull(saved.getId());
        assertEquals("Juan", saved.getNombreCompleto());
        verify(partidoPoliticoRepository).findById(1L);
        verify(candidatoRepository).save(any());
    }

    @Test
    void findByIdWhenNotFoundShouldThrow(){
        when(candidatoRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> candidatoService.findById(5L));
        verify(candidatoRepository).findById(5L);
    }

    @Test
    void findAllShouldReturnList(){
        when(candidatoRepository.findAll()).thenReturn(List.of(new Candidato(), new Candidato()));
        assertEquals(2, candidatoService.findAll().size());
        verify(candidatoRepository).findAll();
    }

}
