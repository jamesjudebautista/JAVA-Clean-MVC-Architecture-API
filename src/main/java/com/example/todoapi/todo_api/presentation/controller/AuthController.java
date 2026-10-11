package com.example.todoapi.todo_api.presentation.controller;


import com.example.todoapi.todo_api.domain.model.User;
import com.example.todoapi.todo_api.domain.usecase.LoginUseCase;
import com.example.todoapi.todo_api.domain.usecase.RegisterUseCase;
import com.example.todoapi.todo_api.presentation.dto.LoginRequest;
import com.example.todoapi.todo_api.presentation.dto.RegisterRequest;
import com.example.todoapi.todo_api.presentation.dto.UserResponse;
import com.example.todoapi.todo_api.utils.Constant;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    private  final LoginUseCase loginUseCase;

    public AuthController(RegisterUseCase registerUseCase, LoginUseCase loginUseCase){
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
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


    @PostMapping(Constant.login)
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest request){

        User userData = loginUseCase.execute(request);
        return ResponseEntity.ok(new UserResponse(
                userData.getId(), userData.getFirstName(), userData.getMiddleName(),
                userData.getLastName(), userData.getUsername(), userData.getAge(),
                userData.getAddress(), userData.getEmail()));
    }
}
