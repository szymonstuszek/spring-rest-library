package com.kodilla.library.exception;

public class UserNotActiveException extends Exception {
    private String message = "User account is not active";

    public UserNotActiveException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
