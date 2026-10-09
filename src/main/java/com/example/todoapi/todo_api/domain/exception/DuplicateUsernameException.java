package com.example.todoapi.todo_api.domain.exception;

public class DuplicateUsernameException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Username already taken: %s";
    public DuplicateUsernameException(String username) {
        super(String.format(ERROR_MESSAGE, username));
    }
}