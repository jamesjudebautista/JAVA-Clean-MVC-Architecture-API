package com.example.todoapi.todo_api.domain.security;


import org.springframework.stereotype.Component;

@Component
public interface PasswordHasher {
    String hash(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);

}
