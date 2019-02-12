package com.kodilla.library.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kodilla.library.domain.RentalStatus;


public class BookCopyDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private Long bookId;
    private RentalStatus rentalStatus;

    public BookCopyDto(Long bookId, RentalStatus rentalStatus) {
        this.bookId = bookId;
        this.rentalStatus = rentalStatus;
    }

    public BookCopyDto() {

    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }
}
