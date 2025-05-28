package com.tournament.infrastructure.persistence;



import com.tournament.application.repository.PlayerRepository;
import com.tournament.domain.model.Player;
import com.tournament.infrastructure.crypto.Encryptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class JpaPlayerRepositoryAdapter implements PlayerRepository {

    private final SpringDataPlayerRepository jpa;
    private final PlayerPersistenceMapper playerPersistenceMapper;

    public JpaPlayerRepositoryAdapter(Encryptor encryptor, SpringDataPlayerRepository jpa, PlayerPersistenceMapper playerPersistenceMapper) {
        this.playerPersistenceMapper = playerPersistenceMapper;
        this.jpa = jpa;
    }

    @Override
    public void save(Player player) {
        jpa.save(playerPersistenceMapper.toEntity(player));
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpa.existsByUsername(username);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpa.existsById(id);
    }

    @Override
    public List<Player> findAll() {
        return jpa.findAll().stream()
                .map(playerPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Player> findById(UUID id) {
        return jpa.findById(id).map(playerPersistenceMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }

}