package com.tournament.application.service;

import com.tournament.application.port.out.PlayerRepository;
import com.tournament.presentation.exception.PlayerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeletePlayerServiceTest {

    private PlayerRepository playerRepository;
    private DeletePlayerService deletePlayerService;

    @BeforeEach
    void setUp() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        deletePlayerService = new DeletePlayerService(playerRepository);
    }

    @Test
    void shouldDeletePlayerSuccessfully() {
        UUID id = UUID.randomUUID();
        when(playerRepository.existsById(id)).thenReturn(true);

        assertDoesNotThrow(() -> deletePlayerService.deletePlayer(id));
        verify(playerRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenPlayerNotFound() {
        UUID id = UUID.randomUUID();
        when(playerRepository.existsById(id)).thenReturn(false);

        assertThrows(PlayerNotFoundException.class, () -> deletePlayerService.deletePlayer(id));
        verify(playerRepository, never()).deleteById(id);
    }
}
