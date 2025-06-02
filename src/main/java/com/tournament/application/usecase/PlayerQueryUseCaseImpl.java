package com.tournament.application.usecase;

import com.tournament.application.repository.PlayerRepository;
import com.tournament.domain.model.Player;
import com.tournament.presentation.exception.PlayerNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
@Slf4j
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
        log.info("PlayerQueryUseCaseImpl: Return player with Id={}",id);

        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }
}