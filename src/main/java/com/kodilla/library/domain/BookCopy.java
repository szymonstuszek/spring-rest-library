package com.kodilla.library.domain;


import javax.persistence.*;

@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {
    private Long id;
    private Long bookId;
    private RentalStatus rentalStatus;

    public BookCopy(Long id, Long bookId, RentalStatus rentalStatus) {
        this.id = id;
        this.bookId = bookId;
        this.rentalStatus = rentalStatus;
    }

    public BookCopy() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BOOK_COPY")
    public Long getId() {
        return id;
    }

    @Column(name = "BOOK_ID")
    public Long getBookId() {
        return bookId;
    }

    @Column(name = "RENTAL_STATUS", columnDefinition = "enum('AVAILABLE', 'LOST', 'RENTED', 'DAMAGED')")
    @Enumerated(EnumType.STRING)
    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
