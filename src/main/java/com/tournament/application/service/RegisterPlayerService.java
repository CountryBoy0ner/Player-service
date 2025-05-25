package com.tournament.application.service;


import com.tournament.application.port.in.RegisterPlayerUseCase;
import com.tournament.application.port.out.PlayerRepository;
import com.tournament.domain.model.Player;
import com.tournament.presentation.exception.PlayerAlreadyExistsException;
import org.springframework.stereotype.Service;

public class RegisterPlayerService implements RegisterPlayerUseCase {
    private final PlayerRepository repository;

    public RegisterPlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Player register(String username, String email) {
        if (repository.existsByUsername(username)) {
            throw new PlayerAlreadyExistsException("Email already registered: " + email);
        }
        Player player = Player.create(username, email);
        repository.save(player);
        return player;
    }
}