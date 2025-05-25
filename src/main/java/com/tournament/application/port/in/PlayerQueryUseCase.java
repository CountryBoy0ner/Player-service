package com.tournament.application.port.in;

import com.tournament.domain.model.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerQueryUseCase {
    List<Player> getAllPlayers();
    Player getPlayerById(UUID id);
}