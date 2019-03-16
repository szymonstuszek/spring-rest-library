package com.kodilla.library.service;

import com.kodilla.library.domain.Book;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(final Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(final Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(final Long id) {
        bookRepository.delete(id);
    }
}
