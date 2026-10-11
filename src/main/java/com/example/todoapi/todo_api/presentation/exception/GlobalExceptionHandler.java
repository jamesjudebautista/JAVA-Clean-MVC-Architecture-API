package com.example.todoapi.todo_api.presentation.exception;

import com.example.todoapi.todo_api.domain.exception.DuplicateUsernameException;
import com.example.todoapi.todo_api.domain.exception.InvalidCredentialsException;
import org.hibernate.DuplicateMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static String Error = "error";

    @ExceptionHandler({DuplicateUsernameException.class, DuplicateMappingException.class})
    public ResponseEntity<Map<String, String>> handleDuplicate(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(Error, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(),e.getDefaultMessage()));

        return ResponseEntity.badRequest().body(Map.of(Error, "Validation failed", "fields",errors));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String,String>> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(Error,ex.getMessage()));
    }
}
