package com.example.todoapi.todo_api.config;

import com.example.todoapi.todo_api.domain.security.PasswordHasher;
import com.example.todoapi.todo_api.domain.repository.UserRepository;
import com.example.todoapi.todo_api.domain.usecase.LoginUseCase;
import com.example.todoapi.todo_api.domain.usecase.RegisterUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class UseCaseConfig  {

    @Bean
    public RegisterUseCase registerUseCase(UserRepository userRepository,
                                           PasswordHasher passwordHasher) {
        return new RegisterUseCase(userRepository, passwordHasher);
    }

    @Bean
    public LoginUseCase loginUseCase(UserRepository userRepository,
                                     PasswordHasher passwordHasher) {
        return new LoginUseCase(userRepository, passwordHasher);
    }

}
