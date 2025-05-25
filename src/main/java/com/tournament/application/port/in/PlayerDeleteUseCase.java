package com.tournament.application.port.in;

import java.util.UUID;

public interface PlayerDeleteUseCase {
    void deletePlayer(UUID id);
}