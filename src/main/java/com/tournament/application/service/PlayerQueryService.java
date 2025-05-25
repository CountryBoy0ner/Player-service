package com.tournament.application.service;

import com.tournament.application.port.in.PlayerQueryUseCase;
import com.tournament.application.port.out.PlayerRepository;
import com.tournament.domain.model.Player;
import com.tournament.presentation.exception.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayerQueryService implements PlayerQueryUseCase {
    private final PlayerRepository playerRepository;

    public PlayerQueryService(PlayerRepository repository) {
        this.playerRepository = repository;
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(UUID id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }
}