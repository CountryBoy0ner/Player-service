package com.tournament.infrastructure.persistence;


import com.tournament.domain.model.Player;

public class PlayerMapper {
    public static PlayerEntity toEntity(Player player) {
        PlayerEntity e = new PlayerEntity();
        e.setId(player.getId());
        e.setUsername(player.getUsername());
        e.setEmail(player.getEmail());
        e.setRegisteredAt(player.getRegisteredAt());
        return e;
    }

    public static Player toDomain(PlayerEntity entity) {
        return Player.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .registeredAt(entity.getRegisteredAt())
                .build();
    }
}