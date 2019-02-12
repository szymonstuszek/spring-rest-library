package com.kodilla.library.controller;

import com.kodilla.library.domain.dto.BookCopyDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class BookCopyController {

    @RequestMapping(method = RequestMethod.GET, value = "getCopies")
    public List<BookCopyDto> getAllBookCopies() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopy")
    public List<BookCopyDto> getBookCopies(@RequestParam Long id) {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCopy")
    public void deleteBookCopy(@RequestParam Long id) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateCopy")
    public BookCopyDto updateBookCopyDto(@RequestBody BookCopyDto bookCopyDto) {
        return new BookCopyDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addCopy")
    public void addBookCopyDto(@RequestBody BookCopyDto bookCopyDto) {

    }
}
