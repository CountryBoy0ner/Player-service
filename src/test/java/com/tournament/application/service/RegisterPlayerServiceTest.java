package com.tournament.application.service;

import com.tournament.application.port.out.PlayerRepository;
import com.tournament.domain.model.Player;
import com.tournament.presentation.exception.PlayerAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterPlayerServiceTest {

    private PlayerRepository playerRepository;
    private RegisterPlayerService registerPlayerService;

    @BeforeEach
    void setUp() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        registerPlayerService = new RegisterPlayerService(playerRepository);
    }

    @Test
    void shouldRegisterPlayerSuccessfully() {
        // given
        String username = "testUser";
        String email = "test@example.com";

        when(playerRepository.existsByUsername(username)).thenReturn(false);

        // when
        Player player = registerPlayerService.register(username, email);

        // then
        assertEquals(username, player.getUsername());
        assertEquals(email, player.getEmail());
        assertNotNull(player.getId());
        assertNotNull(player.getRegisteredAt());
        verify(playerRepository, times(1)).save(any(Player.class));
    }

    @Test
    void shouldThrowExceptionWhenUsernameExists() {
        // given
        String username = "testUser";
        String email = "test@example.com";

        when(playerRepository.existsByUsername(username)).thenReturn(true);

        // then
        assertThrows(PlayerAlreadyExistsException.class, () -> {
            registerPlayerService.register(username, email);
        });

        verify(playerRepository, never()).save(any(Player.class));
    }
}
