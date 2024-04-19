package com.expenseease.group.exceptions;

import com.expenseease.group.dto.ExpenseEaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler({InvalidUserException.class, InvalidGroupException.class})
    public ResponseEntity<ExpenseEaseResponse> getExceptionHandler(Exception exception){
        return new ResponseEntity<>(ExpenseEaseResponse.failure(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
