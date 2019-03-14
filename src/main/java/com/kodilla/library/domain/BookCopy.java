package com.kodilla.library.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {
    private Long id;
    private RentalStatus rentalStatus;
    private Book book;
    private List<Rental> rentals = new ArrayList<>();

    public BookCopy(Long id, RentalStatus rentalStatus, Book book) {
        this.id = id;
        this.rentalStatus = rentalStatus;
        this.book = book;
    }

    public BookCopy() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BOOK_COPY")
    public Long getId() {
        return id;
    }

    @Column(name = "RENTAL_STATUS")
    @Enumerated(EnumType.ORDINAL)
    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    public Book getBook() {
        return book;
    }

    @OneToMany(
            targetEntity = Rental.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "bookCopy"
    )
    public List<Rental> getRentals() {
        return rentals;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopy bookCopy = (BookCopy) o;
        return Objects.equals(id, bookCopy.id) &&
                Objects.equals(book, bookCopy.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book);
    }
}
