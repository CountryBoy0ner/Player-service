package com.tournament.application.service;

import com.tournament.application.port.out.PlayerRepository;
import com.tournament.domain.model.Player;
import com.tournament.presentation.exception.PlayerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerQueryServiceTest {

    private PlayerRepository playerRepository;
    private PlayerQueryService playerQueryService;

    @BeforeEach
    void setUp() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        playerQueryService = new PlayerQueryService(playerRepository);
    }

    @Test
    void shouldReturnPlayerWhenExists() {
        // given
        UUID id = UUID.randomUUID();
        Player player = Player.create("testUser", "test@example.com");
        player = Player.builder()
                .id(id)
                .username(player.getUsername())
                .email(player.getEmail())
                .registeredAt(player.getRegisteredAt())
                .build();

        when(playerRepository.findById(id)).thenReturn(Optional.of(player));

        // when
        Player result = playerQueryService.getPlayerById(id);

        // then
        assertEquals(player, result);
        verify(playerRepository, times(1)).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenPlayerNotFound() {
        // given
        UUID id = UUID.randomUUID();
        when(playerRepository.findById(id)).thenReturn(Optional.empty());

        // then
        assertThrows(PlayerNotFoundException.class, () -> {
            playerQueryService.getPlayerById(id);
        });

        verify(playerRepository, times(1)).findById(id);
    }
}
