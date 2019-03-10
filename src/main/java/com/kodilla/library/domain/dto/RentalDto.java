package com.kodilla.library.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class RentalDto {
    private Long id;
    private Long bookCopyId;
    private Long userId;
    private LocalDate dateOfRental;
    private LocalDate dateOfReturn;
    private LocalDate dueOnDate;

    public RentalDto(Long id, Long bookCopyId, Long userId, LocalDate dateOfRental, LocalDate dateOfReturn, LocalDate dueOnDate) {
        this.id = id;
        this.bookCopyId = bookCopyId;
        this.userId = userId;
        this.dateOfRental = dateOfRental;
        this.dateOfReturn = dateOfReturn;
        this.dueOnDate = dueOnDate;
    }

    public RentalDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(Long bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
