package com.tournament.infrastructure.persistence;

import jakarta.persistence.*; //DTO
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "players")
@Getter
@Setter
public class PlayerEntity {
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDateTime registeredAt;
}