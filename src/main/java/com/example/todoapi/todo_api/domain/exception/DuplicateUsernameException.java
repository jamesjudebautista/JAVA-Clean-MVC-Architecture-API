package com.example.todoapi.todo_api.domain.exception;

import static com.example.todoapi.todo_api.domain.utils.Constant.Error_Message_username_Exsist;

public class DuplicateUsernameException extends RuntimeException {

   public DuplicateUsernameException(String username) {
      super(String.format(Error_Message_username_Exsist, username));
  }
}

