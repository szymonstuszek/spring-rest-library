package com.kodilla.library.controller;

import com.kodilla.library.domain.BookCopyDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookCopyController {

    public List<BookCopyDto> getAllBookCopies() {
        return new ArrayList<>();
    }

    public List<BookCopyDto> getBookCopies(Long id) {
        return new ArrayList<>();
    }

    public void deleteBookCopy(Long id) {

    }

    public BookCopyDto updateBookCopyDto(BookCopyDto bookCopyDto) {
        return new BookCopyDto();
    }

    public void createBookCopyDto(BookCopyDto bookCopyDto) {

    }
}
