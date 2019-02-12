package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class Book {
    private Long id;
    private String title;
    private String author;
    private int yearOfPublishing;

    public Book(Long id, String title, String author, int yearOfPublishing) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
    }

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BOOK")
    public Long getId() {
        return id;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    @Column(name = "YEAR_OF_PUBLISHING")
    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }
}
