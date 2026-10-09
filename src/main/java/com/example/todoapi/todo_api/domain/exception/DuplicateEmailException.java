package com.example.todoapi.todo_api.domain.exception;


public class DuplicateEmailException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Email already registered: %s";

    public DuplicateEmailException(String email) {
        super(String.format(ERROR_MESSAGE, email));
    }
}