package com.kodilla.library.exception;

public class RentalNotPossibleException extends Exception {
    private String message = "Rental error";

    public RentalNotPossibleException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
