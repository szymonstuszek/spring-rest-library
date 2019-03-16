package com.kodilla.library.controller;

import com.kodilla.library.domain.RentalStatus;
import com.kodilla.library.exception.BookNotFoundException;
import com.kodilla.library.domain.dto.BookDto;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.service.BookCopyService;
import com.kodilla.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookCopyService bookCopyService;

    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "books")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(bookService.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "book")
    public BookDto getBook(@RequestParam Long id) throws BookNotFoundException {
        return bookMapper.mapToBookDto(bookService.getBook(id)
                .orElseThrow(BookNotFoundException::new));
    }

    //throws book not found exception
    @RequestMapping(method = RequestMethod.GET, value = "book-count")
    public Long checkNumberOfCopiesAvailableToRent(@RequestParam Long id) {
        return bookCopyService.checkNumberOfCopiesOfBookWithStatus(id, RentalStatus.AVAILABLE);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "book")
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "book")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(bookService.addBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "books", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookMapper.mapToBook(bookDto));
    }
}
