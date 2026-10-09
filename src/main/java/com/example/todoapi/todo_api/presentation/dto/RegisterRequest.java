package com.example.todoapi.todo_api.presentation.dto;

import jakarta.validation.constraints.*;

public record RegisterRequest(
        @NotBlank @Size(max = 50) String firstName,
        @Size(max = 50) String middleName,
        @NotBlank @Size(max = 50) String lastName,
        @NotBlank @Size(min = 5, max = 30) String username,
        @NotBlank @Size(min =6, max = 100) String password,
        @NotNull @Min(13) @Max(120) Integer age,
        @NotBlank @Size(max = 255) String address,
        @NotBlank @Email String email) {
}
