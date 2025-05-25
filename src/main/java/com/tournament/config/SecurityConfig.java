package com.tournament.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Отключаем CSRF (для REST API)
                .authorizeHttpRequests()
                .anyRequest().permitAll()  // Разрешаем доступ ко всем маршрутам
                .and()
                .httpBasic().disable()  // Отключаем базовую аутентификацию
                .formLogin().disable(); // Отключаем форму логина
        return http.build();
    }
}