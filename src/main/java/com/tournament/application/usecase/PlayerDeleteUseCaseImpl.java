package com.tournament.application.usecase;

import com.tournament.application.repository.PlayerRepository;
import com.tournament.presentation.exception.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerDeleteUseCaseImpl implements PlayerDeleteUseCase {

    private final PlayerRepository playerRepository;

    public PlayerDeleteUseCaseImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void deletePlayer(UUID id) {
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException(id);
        }
        playerRepository.deleteById(id);
    }
}