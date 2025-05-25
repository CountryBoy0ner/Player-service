package com.tournament.application.service;

import com.tournament.application.port.in.PlayerDeleteUseCase;
import com.tournament.application.port.out.PlayerRepository;
import com.tournament.presentation.exception.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletePlayerService implements PlayerDeleteUseCase {

    private final PlayerRepository playerRepository;

    public DeletePlayerService(PlayerRepository playerRepository) {
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