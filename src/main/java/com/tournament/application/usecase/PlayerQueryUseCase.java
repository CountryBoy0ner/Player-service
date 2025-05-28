package com.tournament.application.usecase;

import com.tournament.domain.model.Player;

import java.util.List;
import java.util.UUID;

public interface PlayerQueryUseCase {
    List<Player> getAllPlayers();
    Player getPlayerById(UUID id);
}