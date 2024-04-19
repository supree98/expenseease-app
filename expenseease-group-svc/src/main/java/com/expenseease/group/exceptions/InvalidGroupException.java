package com.expenseease.group.exceptions;

public class InvalidGroupException extends RuntimeException{

    public InvalidGroupException() {
    }

    public InvalidGroupException(String message) {
        super(message);
    }

    public InvalidGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGroupException(Throwable cause) {
        super(cause);
    }
}
