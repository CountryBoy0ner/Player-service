package com.tournament.application.usecase;

import com.tournament.application.repository.PlayerRepository;
import com.tournament.presentation.exception.PlayerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
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
        log.info("PlayerDeleteUseCaseImpl deleted user with id ={} ",id);
        playerRepository.deleteById(id);
    }
}