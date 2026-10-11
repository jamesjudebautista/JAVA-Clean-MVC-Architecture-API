package com.example.todoapi.todo_api.domain.exception;

import static com.example.todoapi.todo_api.domain.utils.Constant.Error_Message_Password_Incorrect;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException() {
        super(String.format(Error_Message_Password_Incorrect));
    }

}
