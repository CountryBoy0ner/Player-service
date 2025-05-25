package com.tournament.presentation.dto;

import com.tournament.presentation.exception.FieldValidationError;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private List<FieldValidationError> errors; // Добавляем поле для ошибок
}