package com.tournament.application.port.in;

import com.tournament.domain.model.Player;

public interface RegisterPlayerUseCase {
    Player register(String username, String email);
}