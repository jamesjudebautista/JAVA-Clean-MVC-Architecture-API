package com.example.todoapi.todo_api.presentation.controller;


import com.example.todoapi.todo_api.domain.model.User;
import com.example.todoapi.todo_api.domain.usecase.RegisterUseCase;
import com.example.todoapi.todo_api.presentation.dto.RegisterRequest;
import com.example.todoapi.todo_api.presentation.dto.UserResponse;
import com.example.todoapi.todo_api.utils.Constant;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.AUTH_PATH)
public class AuthController {

    private  final RegisterUseCase registerUseCase;

    public AuthController(RegisterUseCase registerUseCase){
        this.registerUseCase = registerUseCase;
    }

    @PostMapping(Constant.register)
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        User user = new User(null,request.firstName(), request.middleName(), request.lastName(), request.username(),
                request.password(),request.age(),request.address(),request.email());

        User saved = registerUseCase.execute(user);

        UserResponse body = new UserResponse(
                saved.getId(), saved.getFirstName(), saved.getMiddleName(), saved.getLastName(), saved.getUsername(),
                saved.getAge(),saved.getAddress(), saved.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

}
