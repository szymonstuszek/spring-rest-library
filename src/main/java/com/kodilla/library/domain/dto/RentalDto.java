package com.kodilla.library.domain.dto;

import java.time.LocalDate;

public class RentalDto {
    private Long id;
    private LocalDate dateOfRental;
    private LocalDate dateOfReturn;
    private LocalDate dueOnDate;
    private Long userId;
    private Long bookCopyId;

    public RentalDto(Long id,
                     LocalDate dateOfRental,
                     LocalDate dateOfReturn,
                     LocalDate dueOnDate,
                     Long userId,
                     Long bookCopyId) {
        this.id = id;
        this.dateOfRental = dateOfRental;
        this.dateOfReturn = dateOfReturn;
        this.dueOnDate = dueOnDate;
        this.userId = userId;
        this.bookCopyId = bookCopyId;
    }

    public RentalDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfRental() {
        return dateOfRental;
    }

    public void setDateOfRental(LocalDate dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public LocalDate getDueOnDate() {
        return dueOnDate;
    }

    public void setDueOnDate(LocalDate dueOnDate) {
        this.dueOnDate = dueOnDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(Long bookCopyId) {
        this.bookCopyId = bookCopyId;
    }
}
