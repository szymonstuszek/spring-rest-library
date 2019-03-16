package com.kodilla.library.controller;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.dto.BookCopyDto;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.mapper.BookCopyMapper;
import com.kodilla.library.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class BookCopyController {

    @Autowired
    private BookCopyService bookCopyService;

    @Autowired
    private BookCopyMapper bookCopyMapper;

    @RequestMapping(method = RequestMethod.GET, value = "copies")
    public List<BookCopyDto> getAllBookCopies() {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyService.getAllBookCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "copy")
    public BookCopyDto getBookCopy(@RequestParam Long id) throws BookCopyNotFoundException {
        return bookCopyMapper.mapToBookCopyDto(bookCopyService.getBookCopy(id).orElseThrow(BookCopyNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "copy/available")
    public void markBookAsAvailable(@RequestParam Long id) {
        bookCopyService.markBookAsAvailable(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "copy/lost")
    public void markBookAsLost(@RequestParam Long id) {
        bookCopyService.markBookAsLost(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "copy/damaged")
    public void markBookAsDamaged(@RequestParam Long id) {
        bookCopyService.markBookAsDamaged(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "copy/rented")
    public void markBookAsRented(@RequestParam Long id) {
        bookCopyService.markBookAsRented(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "copy")
    public void deleteBookCopy(@RequestParam Long id) {
        bookCopyService.deleteBookCopy(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "copy")
    public BookCopyDto updateBookCopyDto(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = bookCopyService.addBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
        return bookCopyMapper.mapToBookCopyDto(bookCopy);
    }

    @RequestMapping(method = RequestMethod.POST, value = "copies", consumes = APPLICATION_JSON_VALUE)
    public void addBookCopyDto(@RequestBody BookCopyDto bookCopyDto) {
        bookCopyService.addBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }
}
