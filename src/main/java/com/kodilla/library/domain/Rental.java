package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RENTALS")
public class Rental {
    private Long id;
    private Long bookCopyId;
    private Long userId;
    private LocalDate dateOfRental;
    private LocalDate dateOfReturn;
    private LocalDate dueOnDate;

    public Rental(Long id, Long bookCopyId, Long userId, LocalDate dateOfRental, LocalDate dateOfReturn, LocalDate dueOnDate) {
        this.id = id;
        this.bookCopyId = bookCopyId;
        this.userId = userId;
        this.dateOfRental = dateOfRental;
        this.dateOfReturn = dateOfReturn;
        this.dueOnDate = dueOnDate;
    }

    public Rental() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RENTAL")
    public Long getId() {
        return id;
    }

    @Column(name = "BOOK_COPY_ID")
    public Long getBookCopyId() {
        return bookCopyId;
    }

    @Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    @Column(name = "DATE_OF_RENTAL")
    public LocalDate getDateOfRental() {
        return dateOfRental;
    }

    @Column(name = "DATE_OF_RETURN")
    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    @Column(name = "DUE_ON_DATE")
    public LocalDate getDueOnDate() {
        return dueOnDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookCopyId(Long bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDateOfRental(LocalDate dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public void setDueOnDate(LocalDate dueOnDate) {
        this.dueOnDate = dueOnDate;
    }


}
