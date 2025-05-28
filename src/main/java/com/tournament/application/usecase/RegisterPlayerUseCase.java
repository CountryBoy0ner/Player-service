package com.tournament.application.usecase;

import com.tournament.domain.model.Player;

public interface RegisterPlayerUseCase {
    Player register(String username, String email);
}