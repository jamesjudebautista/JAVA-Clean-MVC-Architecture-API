package com.example.todoapi.todo_api.domain.usecase;

import com.example.todoapi.todo_api.domain.exception.InvalidCredentialsException;
import com.example.todoapi.todo_api.domain.model.User;
import com.example.todoapi.todo_api.domain.repository.UserRepository;
import com.example.todoapi.todo_api.domain.security.PasswordHasher;
import com.example.todoapi.todo_api.presentation.dto.LoginRequest;


public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public LoginUseCase(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public  User execute(LoginRequest user){
        User userData = userRepository.findByUsername(user.username())
                .orElseThrow(InvalidCredentialsException::new);

        if(passwordHasher.matches(user.password(),userData.getPassword())){
            throw new InvalidCredentialsException();
        }
        return userData;
    }
}
