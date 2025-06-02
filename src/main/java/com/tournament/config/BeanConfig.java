package com.tournament.config;

import com.tournament.application.usecase.PlayerDeleteUseCase;
import com.tournament.application.usecase.PlayerQueryUseCase;
import com.tournament.application.usecase.RegisterPlayerUseCase;
import com.tournament.application.repository.PlayerRepository;
import com.tournament.application.usecase.PlayerDeleteUseCaseImpl;
import com.tournament.application.usecase.PlayerQueryUseCaseImpl;
import com.tournament.application.usecase.RegisterPlayerUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public RegisterPlayerUseCase registerPlayerUseCase(PlayerRepository repo) {
        return new RegisterPlayerUseCaseImpl(repo);
    }

    @Bean
    public PlayerQueryUseCase playerQueryUseCase(PlayerRepository repo) {
        return new PlayerQueryUseCaseImpl(repo);
    }

    @Bean
    public PlayerDeleteUseCase playerDeleteUseCase(PlayerRepository repo) {
        return new PlayerDeleteUseCaseImpl(repo);
    }
}
