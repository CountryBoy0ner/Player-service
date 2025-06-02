package com.tournament.infrastructure.persistence;



import com.tournament.application.repository.PlayerRepository;
import com.tournament.domain.model.Player;
import com.tournament.infrastructure.crypto.Encryptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Slf4j
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
        log.info("JpaPlayerRepositoryAdapter: save user={}",player);
        jpa.save(playerPersistenceMapper.toEntity(player));
    }

    @Override
    public boolean existsByUsername(String username) {
        log.info("JpaPlayerRepositoryAdapter: existsByUsername username={}",username);
        return jpa.existsByUsername(username);
    }

    @Override
    public boolean existsById(UUID id) {
        log.info("JpaPlayerRepositoryAdapter: existsByUid id={}",id);
        return jpa.existsById(id);
    }

    @Override
    public List<Player> findAll() {
        log.info("JpaPlayerRepositoryAdapter: findAll");
        return jpa.findAll().stream()
                .map(playerPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Player> findById(UUID id) {
        log.info("JpaPlayerRepositoryAdapter: findById id={}", id);
        return jpa.findById(id).map(playerPersistenceMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("JpaPlayerRepositoryAdapter: deleteById id={}", id);
        jpa.deleteById(id);
    }

}