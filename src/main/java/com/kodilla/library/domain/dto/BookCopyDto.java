package com.kodilla.library.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.enums.RentalStatus;


public class BookCopyDto {
    private Long id;
    private Long bookId;
    private RentalStatus rentalStatus;

    public BookCopyDto(Long id, RentalStatus rentalStatus, Long bookId) {
        this.id = id;
        this.bookId = bookId;
        this.rentalStatus = rentalStatus;
    }

    public BookCopyDto() {

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
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
