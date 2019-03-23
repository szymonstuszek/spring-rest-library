package com.kodilla.library.controller;

import com.kodilla.library.domain.enums.RentalStatus;
import com.kodilla.library.domain.dto.BookDto;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.service.BookCopyService;
import com.kodilla.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/library",
        produces = APPLICATION_JSON_VALUE
)
@CrossOrigin("*")
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
    public BookDto getBook(@RequestParam Long id) throws NotFoundException {
        return bookMapper.mapToBookDto(bookService.getBook(id)
                .orElseThrow(NotFoundException::new));
    }

    //throws book not found exception
    @RequestMapping(method = RequestMethod.GET, value = "book/count/{id}")
    public Long checkNumberOfCopiesAvailableToRent(@PathVariable("id") Long id) {
        return bookCopyService.checkNumberOfCopiesOfBookWithStatus(id, RentalStatus.AVAILABLE);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "books")
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "books")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(bookService.addBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "books")
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookMapper.mapToBook(bookDto));
    }
}
