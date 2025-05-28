package com.tournament.application.usecase;

import com.tournament.application.repository.PlayerRepository;
import com.tournament.domain.model.Player;
import com.tournament.presentation.exception.PlayerNotFoundException;

import java.util.List;
import java.util.UUID;

public class PlayerQueryUseCaseImpl implements PlayerQueryUseCase {
    private final PlayerRepository playerRepository;

    public PlayerQueryUseCaseImpl(PlayerRepository repository) {
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