package com.kodilla.library.exception;

public class BookCopyNotAvailableException extends Exception {
    private String message = "Book copy not available error";

    public BookCopyNotAvailableException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
