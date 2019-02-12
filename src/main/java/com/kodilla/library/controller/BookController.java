package com.kodilla.library.controller;

import com.kodilla.library.domain.BookDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    public List<BookDto> getBooks() {
        return new ArrayList<>();
    }

    public BookDto getBook(Long id) {
        return new BookDto();
    }

    public void deleteBook(Long id) {

    }

    public BookDto updateBook(BookDto bookDto) {
        return new BookDto();
    }

    public void createBook(BookDto bookDto) {

    }
}
