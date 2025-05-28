package com.tournament.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tournament.application.usecase.PlayerDeleteUseCase;
import com.tournament.application.usecase.PlayerQueryUseCase;
import com.tournament.application.usecase.RegisterPlayerUseCase;
import com.tournament.domain.model.Player;
import com.tournament.presentation.dto.PlayerRequest;
import com.tournament.presentation.dto.PlayerResponse;
import com.tournament.presentation.mapper.PlayerDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class)
@AutoConfigureMockMvc(addFilters = false) // отключаем security фильтры в тестах
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegisterPlayerUseCase registerPlayerUseCase;

    @MockBean
    private PlayerQueryUseCase playerQueryUseCase;

    @MockBean
    private PlayerDeleteUseCase playerDeleteUseCase;

    @MockBean
    private PlayerDtoMapper playerDtoMapper;

    @BeforeEach
    void setUp() {
        when(playerDtoMapper.toResponse(any(Player.class))).thenAnswer(invocation -> {
            Player player = invocation.getArgument(0);
            PlayerResponse response = new PlayerResponse();
            response.setId(player.getId().toString());
            response.setUsername(player.getUsername());
            response.setEmail(player.getEmail());
            response.setRegisteredAt(player.getRegisteredAt().toString());
            return response;
        });
    }

    @Test
    void shouldRegisterPlayer() throws Exception {
        PlayerRequest request = new PlayerRequest("testUser", "test@example.com");
        Player player = Player.create("testUser", "test@example.com");

        when(registerPlayerUseCase.register(eq(request.getUsername()), eq(request.getEmail())))
                .thenReturn(player);

        mockMvc.perform(post("/players/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void shouldReturnAllPlayers() throws Exception {
        Player player = Player.create("testUser", "test@example.com");

        when(playerQueryUseCase.getAllPlayers()).thenReturn(List.of(player));

        mockMvc.perform(get("/players/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("testUser"))
                .andExpect(jsonPath("$[0].email").value("test@example.com"));
    }

    @Test
    void shouldReturnPlayerById() throws Exception {
        UUID id = UUID.randomUUID();
        Player player = Player.create("testUser", "test@example.com");

        when(playerQueryUseCase.getPlayerById(eq(id))).thenReturn(player);

        mockMvc.perform(get("/players/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void shouldDeletePlayerById() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(playerDeleteUseCase).deletePlayer(eq(id));

        mockMvc.perform(delete("/players/" + id))
                .andExpect(status().isNoContent());
    }
}
