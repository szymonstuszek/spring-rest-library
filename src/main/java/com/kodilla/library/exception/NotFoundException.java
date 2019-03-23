package com.kodilla.library.exception;

public class NotFoundException extends Exception {
    private String message = "Not found.";

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
