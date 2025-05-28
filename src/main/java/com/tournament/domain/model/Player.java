package com.tournament.domain.model;

import lombok.Value;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class Player {
    UUID id;
    String username;
    String email;
    LocalDateTime registeredAt;

    public static Player create(String username, String email) {
        return Player.builder()
                .id(UUID.randomUUID())
                .username(username)
                .email(email)
                .registeredAt(LocalDateTime.now())
                .build();
    }
}
