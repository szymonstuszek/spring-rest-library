package com.kodilla.library.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.User;

import java.time.LocalDate;

public class RentalDto {
    private Long id;
    private LocalDate dateOfRental;
    private LocalDate dateOfReturn;
    private LocalDate dueOnDate;
    private User user;
    private BookCopy bookCopy;

    public RentalDto(Long id,
                     LocalDate dateOfRental,
                     LocalDate dateOfReturn,
                     LocalDate dueOnDate,
                     User user,
                     BookCopy bookCopy) {
        this.id = id;
        this.dateOfRental = dateOfRental;
        this.dateOfReturn = dateOfReturn;
        this.dueOnDate = dueOnDate;
        this.user = user;
        this.bookCopy = bookCopy;
    }

    public RentalDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonFormat(pattern="dd-MM-yyyy")
    public LocalDate getDateOfRental() {
        return dateOfRental;
    }

    public void setDateOfRental(LocalDate dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    @JsonFormat(pattern="dd-MM-yyyy")
    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    @JsonFormat(pattern="dd-MM-yyyy")
    public LocalDate getDueOnDate() {
        return dueOnDate;
    }

    public void setDueOnDate(LocalDate dueOnDate) {
        this.dueOnDate = dueOnDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }
}
