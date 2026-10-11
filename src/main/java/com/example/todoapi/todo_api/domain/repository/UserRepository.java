package com.example.todoapi.todo_api.domain.repository;

import com.example.todoapi.todo_api.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
