package com.tournament.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPlayerRepository extends JpaRepository<PlayerEntity, UUID> {
    boolean existsByUsername(String username);
}