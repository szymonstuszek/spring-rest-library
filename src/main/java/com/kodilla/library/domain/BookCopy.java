package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOOK_COPIES")
public class BookCopy {
    private Long id;
    private Long bookId;

    //enum: available, lost, rented
    private String status;

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

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
