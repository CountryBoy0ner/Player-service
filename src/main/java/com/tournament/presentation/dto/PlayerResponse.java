package com.tournament.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerResponse {
    private String id;
    private String username;
    private String email;
    private String registeredAt;
}