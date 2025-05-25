package com.tournament.presentation.dto;



import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Data
public class PlayerRequest {

    @NotBlank(message = "Username must not be empty")
    private String username;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Invalid email format")
    private String email;

    public PlayerRequest(String testUser, String mail) {
        this.email = mail;
        this.username = testUser;
    }
}
