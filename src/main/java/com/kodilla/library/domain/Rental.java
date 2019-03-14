package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RENTALS")
public class Rental {
    private Long id;
    private LocalDate dateOfRental;
    private LocalDate dateOfReturn;
    private LocalDate dueOnDate;
    private User user;
    private BookCopy bookCopy;

    public Rental(Long id, LocalDate dateOfRental, LocalDate dateOfReturn, LocalDate dueOnDate) {
        this.id = id;
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

    @Column(name = "DATE_OF_RENTAL")
    @JsonFormat(pattern="dd-MM-yyyy")
    public LocalDate getDateOfRental() {
        return dateOfRental;
    }

    @Column(name = "DATE_OF_RETURN")
    @JsonFormat(pattern="dd-MM-yyyy")
    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    @Column(name = "DUE_ON_DATE")
    @JsonFormat(pattern="dd-MM-yyyy")
    public LocalDate getDueOnDate() {
        return dueOnDate;
    }

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "BOOK_COPY_ID")
    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }
}
