package com.example.todoapi.todo_api.domain.usecase;

import com.example.todoapi.todo_api.domain.exception.DuplicateEmailException;
import com.example.todoapi.todo_api.domain.security.PasswordHasher;
import com.example.todoapi.todo_api.domain.model.User;
import com.example.todoapi.todo_api.domain.repository.UserRepository;
import com.example.todoapi.todo_api.domain.exception.DuplicateUsernameException;

public class RegisterUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public RegisterUseCase(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public User execute(User user){

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateUsernameException(user.getUsername());
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailException(user.getEmail());
        }

        User toSave = new User(
                null,
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getUsername(),
                passwordHasher.hash(user.getPassword()),
                user.getAge(),
                user.getAddress(),
                user.getEmail());
        return userRepository.save(toSave);
    }
}
