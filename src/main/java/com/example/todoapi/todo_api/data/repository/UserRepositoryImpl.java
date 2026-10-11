package com.example.todoapi.todo_api.data.repository;

import com.example.todoapi.todo_api.data.jpa.UserJPARepository;
import com.example.todoapi.todo_api.data.mapper.UserMapper;
import com.example.todoapi.todo_api.domain.model.User;
import com.example.todoapi.todo_api.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJPARepository jpa;
    private final UserMapper mapper;

    public UserRepositoryImpl(UserJPARepository jpa, UserMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }


    @Override
    public User save(User user) {
        return mapper.toDomain(jpa.save(mapper.toEntity(user)));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpa.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpa.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpa.existsByEmail(email);
    }

}
