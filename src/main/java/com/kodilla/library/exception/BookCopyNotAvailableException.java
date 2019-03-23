package com.kodilla.library.exception;

public class BookCopyNotAvailableException extends Exception {
    private String message = "Book copy is not available.";

    public BookCopyNotAvailableException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
