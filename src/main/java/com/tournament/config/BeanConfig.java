package com.tournament.config;

import com.tournament.application.port.in.PlayerDeleteUseCase;
import com.tournament.application.port.in.PlayerQueryUseCase;
import com.tournament.application.port.in.RegisterPlayerUseCase;
import com.tournament.application.port.out.PlayerRepository;
import com.tournament.application.service.DeletePlayerService;
import com.tournament.application.service.PlayerQueryService;
import com.tournament.application.service.RegisterPlayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public RegisterPlayerUseCase registerPlayerUseCase(PlayerRepository repo) {
        return new RegisterPlayerService(repo);
    }
    @Bean
    public PlayerQueryUseCase playerQueryUseCase(PlayerRepository repo) {
        return new PlayerQueryService(repo);
    }
    @Bean
    public PlayerDeleteUseCase playerDeleteUseCase(PlayerRepository repo) {
        return new DeletePlayerService(repo);
    }
}