package com.tournament.infrastructure.persistence;


import com.tournament.domain.model.Player;

import com.tournament.infrastructure.crypto.Encryptor;
import org.springframework.stereotype.Component;

@Component
public class PlayerPersistenceMapper {

    private final Encryptor encryptor;

    public PlayerPersistenceMapper(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    public PlayerEntity toEntity(Player player) {
        PlayerEntity entity = new PlayerEntity();
        entity.setId(player.getId());
        entity.setUsername(player.getUsername());
        entity.setEmail(player.getEmail() != null ? encryptor.encrypt(player.getEmail()) : null);
        entity.setRegisteredAt(player.getRegisteredAt());
        return entity;
    }

    public Player toDomain(PlayerEntity entity) {
        return Player.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail() != null ? encryptor.decrypt(entity.getEmail()) : null)
                .registeredAt(entity.getRegisteredAt())
                .build();
    }
}