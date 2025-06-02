package com.tournament.application.usecase;


import com.tournament.application.repository.PlayerRepository;
import com.tournament.domain.model.Player;
import com.tournament.domain.model.PlayerRegisteredEvent;
import com.tournament.presentation.exception.PlayerAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
@Slf4j
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
        log.info("RegisterPlayerUseCaseImp: Save user ={} into bd",player);
        PlayerRegisteredEvent event = new PlayerRegisteredEvent(player.getId(), player.getUsername(), player.getEmail());
        return player;
    }
}