package com.example.todoapi.todo_api.presentation.dto;

public record UserResponse(
        Long id,
        String firstName,
        String middleName,
        String lastName,
        String username,
        Integer age,
        String address,
        String email) {
}