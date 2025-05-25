package com.tournament.presentation.mapper;

import com.tournament.domain.model.Player;
import com.tournament.presentation.dto.PlayerResponse;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerResponse toResponse(Player player) {
        PlayerResponse response = new PlayerResponse();
        response.setId(player.getId().toString());
        response.setUsername(player.getUsername());
        response.setEmail(player.getEmail());
        response.setRegisteredAt(player.getRegisteredAt().toString());
        return response;
    }
}