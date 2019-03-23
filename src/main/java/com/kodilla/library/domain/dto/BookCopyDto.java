package com.kodilla.library.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.enums.RentalStatus;


public class BookCopyDto {
    private Long id;
    private Book book;
    private RentalStatus rentalStatus;

    public BookCopyDto(Long id, RentalStatus rentalStatus, Book book) {
        this.id = id;
        this.book = book;
        this.rentalStatus = rentalStatus;
    }

    public BookCopyDto() {

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }
}
