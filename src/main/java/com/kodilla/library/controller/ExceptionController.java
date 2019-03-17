package com.kodilla.library.controller;

import com.kodilla.library.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ RentalNotPossibleException.class })
    public ResponseEntity<Object> handleRentalNotPossibleException(
            RentalNotPossibleException ex, WebRequest request) {
        String error = "Cannot create rental - either account is disabled or book is not available.";

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ BookCopyNotAvailableException.class })
    public ResponseEntity<Object> handleBookCopyNotAvailableException(
            BookCopyNotAvailableException ex, WebRequest request) {
        String error = "Cannot create rental - book is not available.";

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ BookCopyNotFoundException.class })
    public ResponseEntity<Object> handleBookCopyNotFoundException(
            BookCopyNotAvailableException ex, WebRequest request) {
        String error = "Book copy not found.";

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ UserNotFoundException.class })
    public ResponseEntity<Object> handleUserNotFoundException(
            BookCopyNotAvailableException ex, WebRequest request) {
        String error = "User not found.";

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ RentalNotFoundException.class })
    public ResponseEntity<Object> handleRentalNotFoundException(
            BookCopyNotAvailableException ex, WebRequest request) {
        String error = "Rental not found.";

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

}
