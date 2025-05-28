package com.tournament.presentation.controller;

import com.tournament.application.usecase.PlayerDeleteUseCase;
import com.tournament.application.usecase.PlayerQueryUseCase;
import com.tournament.application.usecase.RegisterPlayerUseCase;
import com.tournament.domain.model.Player;
import com.tournament.presentation.dto.PlayerRequest;
import com.tournament.presentation.dto.PlayerResponse;
import com.tournament.presentation.mapper.PlayerDtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/players")
@Validated
public class PlayerController {

    private final RegisterPlayerUseCase registerPlayerUseCase;
    private final PlayerDtoMapper playerDtoMapper;
    private final PlayerQueryUseCase playerQueryUseCase;
    private final PlayerDeleteUseCase playerDeleteUseCase;

    public PlayerController(RegisterPlayerUseCase useCase, PlayerDtoMapper playerDtoMapper, PlayerQueryUseCase playerQueryUseCase, PlayerDeleteUseCase playerDeleteUseCase) {
        this.registerPlayerUseCase = useCase;
        this.playerDtoMapper = playerDtoMapper;
        this.playerQueryUseCase = playerQueryUseCase;
        this.playerDeleteUseCase = playerDeleteUseCase;
    }


    @PostMapping("/register") //todo clean RESTful API
    public ResponseEntity<PlayerResponse> register(@Valid @RequestBody PlayerRequest request) {
        // Вызов бизнес-логики (UseCase)
        Player player = registerPlayerUseCase.register(request.getUsername(), request.getEmail());
        System.out.println(player.getUsername() + " " + player.getEmail() + " was caught in controller");
        // Преобразование через маппер
        PlayerResponse response = playerDtoMapper.toResponse(player);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")//todo clean RESTful API
    public ResponseEntity<List<PlayerResponse>> getAllPlayers() {
        List<Player> players = playerQueryUseCase.getAllPlayers();
        List<PlayerResponse> responses = players.stream().map(playerDtoMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getPlayerById(@PathVariable UUID id) {
        Player player = playerQueryUseCase.getPlayerById(id);
        return ResponseEntity.ok(playerDtoMapper.toResponse(player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable UUID id) {
        playerDeleteUseCase.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}