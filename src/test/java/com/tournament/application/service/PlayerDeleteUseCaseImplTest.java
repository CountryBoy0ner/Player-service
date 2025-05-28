package com.tournament.application.service;

import com.tournament.application.repository.PlayerRepository;
import com.tournament.application.usecase.PlayerDeleteUseCaseImpl;
import com.tournament.presentation.exception.PlayerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerDeleteUseCaseImplTest {

    private PlayerRepository playerRepository;
    private PlayerDeleteUseCaseImpl playerDeleteUseCaseImpl;

    @BeforeEach
    void setUp() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        playerDeleteUseCaseImpl = new PlayerDeleteUseCaseImpl(playerRepository);
    }

    @Test
    void shouldDeletePlayerSuccessfully() {
        UUID id = UUID.randomUUID();
        when(playerRepository.existsById(id)).thenReturn(true);

        assertDoesNotThrow(() -> playerDeleteUseCaseImpl.deletePlayer(id));
        verify(playerRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenPlayerNotFound() {
        UUID id = UUID.randomUUID();
        when(playerRepository.existsById(id)).thenReturn(false);

        assertThrows(PlayerNotFoundException.class, () -> playerDeleteUseCaseImpl.deletePlayer(id));
        verify(playerRepository, never()).deleteById(id);
    }
}
