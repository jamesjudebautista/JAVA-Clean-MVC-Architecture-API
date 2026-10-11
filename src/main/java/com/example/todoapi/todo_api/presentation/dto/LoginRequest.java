package com.example.todoapi.todo_api.presentation.dto;

import jakarta.validation.constraints.*;

public record LoginRequest(
        @NotBlank @Size(min = 5, max = 30) String username,
        @NotBlank @Size(min =6, max = 100) String password) {
}
