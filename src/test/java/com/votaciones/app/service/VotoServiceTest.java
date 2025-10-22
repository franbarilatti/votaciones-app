package com.votaciones.app.service;

import com.votaciones.app.exception.ResourceNotFoundException;
import com.votaciones.app.model.Candidato;
import com.votaciones.app.model.Voto;
import com.votaciones.app.repository.CandidatoRepository;
import com.votaciones.app.repository.VotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private CandidatoRepository candidatoRepository;

    @InjectMocks
    private VotoServiceImpl votoService;

    @Test
    void registerVote_whenCandidatoNotFound_shouldThrow() {
        when(candidatoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> votoService.registerVote(99L));
        verify(candidatoRepository).findById(99L);
        verifyNoInteractions(votoRepository);
    }

    @Test
    void registerVote_whenCandidatoExists_shouldSave() {
        Candidato c = new Candidato();
        c.setId(1L);
        when(candidatoRepository.findById(1L)).thenReturn(Optional.of(c));
        when(votoRepository.save(any(Voto.class))).thenAnswer(inv -> {
            Voto v = inv.getArgument(0);
            v.setId(10L);
            return v;
        });

        Voto v = votoService.registerVote(1L);
        assertNotNull(v);
        assertEquals(10L, v.getId());
        verify(votoRepository).save(any(Voto.class));
    }

    @Test
    void countVotesByCandidato_shouldCallRepo() {
        when(votoRepository.countByCandidatoId(1L)).thenReturn(5L);
        long count = votoService.countVotesByCandidato(1L);
        assertEquals(5L, count);
        verify(votoRepository).countByCandidatoId(1L);
    }

    @Test
    void countVotesByPartido_whenNoRows_shouldReturnZero() {
        when(votoRepository.countVotosGroupedByPartido()).thenReturn(List.of());
        long result = votoService.countVotesByPartido(1L);
        assertEquals(0L, result);
    }

    @Test
    void votosPorPartido_shouldReturnRows() {
        Object[] row = new Object[]{1L, 3L};
        when(votoRepository.countVotosGroupedByPartido()).thenReturn(Collections.singletonList(row));
        List<Object[]> rows = votoService.votesForPartido();
        assertEquals(1, rows.size());
    }
}