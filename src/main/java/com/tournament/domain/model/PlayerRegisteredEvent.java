package com.tournament.domain.model;

import java.util.UUID;

public record PlayerRegisteredEvent(UUID playerId, String playerName, String email) {}
