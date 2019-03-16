package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    public Rental(Long id,
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

    public Rental(LocalDate dateOfRental, LocalDate dateOfReturn,
                      LocalDate dueOnDate, User user,
                      BookCopy bookCopy) {
            this.dateOfRental = dateOfRental;
            this.dateOfReturn = dateOfReturn;
            this.dueOnDate = dueOnDate;
            this.user = user;
            this.bookCopy = bookCopy;
    }

    public Rental() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    public User getUser() {
        return user;
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
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
