package com.tournament.application.port.out;


import com.tournament.domain.model.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository {
    void save(Player player);
    boolean existsByUsername(String username);
    boolean existsById(UUID id);
    List<Player> findAll();
    Optional<Player> findById(UUID id);
    void deleteById(UUID id);

}