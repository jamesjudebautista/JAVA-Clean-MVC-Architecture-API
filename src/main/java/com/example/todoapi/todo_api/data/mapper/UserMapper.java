package com.example.todoapi.todo_api.data.mapper;

import com.example.todoapi.todo_api.data.entity.UserEntity;
import com.example.todoapi.todo_api.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity e) {
        return new User(e.getId(), e.getFirstName(), e.getMiddleName(),
                e.getLastName(), e.getUsername(), e.getPassword(),
                e.getAge(), e.getAddress(), e.getEmail());
    }

    public UserEntity toEntity(User u) {
        UserEntity e = new UserEntity();
        e.setId(u.getId());
        e.setFirstName(u.getFirstName());
        e.setMiddleName(u.getMiddleName());
        e.setLastName(u.getLastName());
        e.setUsername(u.getUsername());
        e.setPassword(u.getPassword());
        e.setAge(u.getAge());
        e.setAddress(u.getAddress());
        e.setEmail(u.getEmail());
        return e;
    }
}