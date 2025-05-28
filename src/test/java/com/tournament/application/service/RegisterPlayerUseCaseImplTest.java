package com.tournament.application.service;

import com.tournament.application.repository.PlayerRepository;
import com.tournament.application.usecase.RegisterPlayerUseCaseImpl;
import com.tournament.domain.model.Player;
import com.tournament.presentation.exception.PlayerAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterPlayerUseCaseImplTest {

    private PlayerRepository playerRepository;
    private RegisterPlayerUseCaseImpl registerPlayerUseCaseImpl;

    @BeforeEach
    void setUp() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        registerPlayerUseCaseImpl = new RegisterPlayerUseCaseImpl(playerRepository);
    }

    @Test
    void shouldRegisterPlayerSuccessfully() {
        // given
        String username = "testUser";
        String email = "test@example.com";

        when(playerRepository.existsByUsername(username)).thenReturn(false);

        // when
        Player player = registerPlayerUseCaseImpl.register(username, email);

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
            registerPlayerUseCaseImpl.register(username, email);
        });

        verify(playerRepository, never()).save(any(Player.class));
    }
}
