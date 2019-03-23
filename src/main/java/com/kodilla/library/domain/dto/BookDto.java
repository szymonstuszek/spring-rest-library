package com.kodilla.library.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


public class BookDto {

    private Long id;
    private String title;
    private String author;
    private int yearOfPublishing;

    public BookDto(String title, String author, int yearOfPublishing) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
    }

    public BookDto() {}

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }
}
