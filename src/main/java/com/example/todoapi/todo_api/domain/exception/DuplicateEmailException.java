package com.example.todoapi.todo_api.domain.exception;


import static com.example.todoapi.todo_api.domain.utils.Constant.Error_Message_Email_Exsist;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super(String.format(Error_Message_Email_Exsist, email));
    }
}