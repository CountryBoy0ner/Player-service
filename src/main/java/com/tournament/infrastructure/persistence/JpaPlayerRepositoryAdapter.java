package com.tournament.infrastructure.persistence;



import com.tournament.application.port.out.PlayerRepository;
import com.tournament.domain.model.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class JpaPlayerRepositoryAdapter implements PlayerRepository {

    private final SpringDataPlayerRepository jpa;

    public JpaPlayerRepositoryAdapter(SpringDataPlayerRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(Player player) {
        jpa.save(PlayerMapper.toEntity(player));
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
        List<PlayerEntity> entities = jpa.findAll();
        return entities.stream()
                .map(PlayerMapper::toDomain) // Преобразуем из Entity в Domain
                .toList();
    }

    @Override
    public Optional<Player> findById(UUID id) {
        return jpa.findById(id).map(PlayerMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }
}