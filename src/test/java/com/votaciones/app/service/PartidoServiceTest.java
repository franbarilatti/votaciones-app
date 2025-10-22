package com.votaciones.app.service;

import com.votaciones.app.exception.ResourceNotFoundException;
import com.votaciones.app.model.PartidoPolitico;
import com.votaciones.app.repository.PartidoPoliticoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PartidoServiceTest {

    @Mock
    private PartidoPoliticoRepository repository;

    @InjectMocks
    private PartidoPoliticoServiceImpl service;

    @Test
    void createShouldSaveAndReturn(){
        PartidoPolitico partido = new PartidoPolitico("Test", "TS");
        PartidoPolitico saved = new PartidoPolitico("Test", "TS");

        saved.setId(1L);

        when(repository.save(partido)).thenReturn(saved);

        PartidoPolitico result = service.create(partido);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test", result.getNombre());
        verify(repository, times(1)).save(partido);
    }

    @Test
    void findByIdWhenNotFoundShouldThrow(){
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findById(99L));
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void findAllShouldReturnList(){
        when(repository.findAll()).thenReturn(List.of(new PartidoPolitico("A", "A"), new PartidoPolitico("B", "B")));
        List<PartidoPolitico> list = service.findAll();
        assertEquals(2, list.size());
        verify(repository).findAll();
    }



}
