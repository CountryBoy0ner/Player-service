package com.tournament.application.usecase;


import com.tournament.application.repository.PlayerRepository;
import com.tournament.domain.model.Player;
import com.tournament.presentation.exception.PlayerAlreadyExistsException;

public class RegisterPlayerUseCaseImpl implements RegisterPlayerUseCase {
    private final PlayerRepository repository;

    public RegisterPlayerUseCaseImpl(PlayerRepository repository) {
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