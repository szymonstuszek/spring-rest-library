package com.kodilla.library.service;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public Optional<BookCopy> getBookCopy(final Long id) {
        return bookCopyRepository.findById(id);
    }

    public BookCopy addBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public void deleteBookCopy(final Long id) {
        bookCopyRepository.delete(id);
    }
}