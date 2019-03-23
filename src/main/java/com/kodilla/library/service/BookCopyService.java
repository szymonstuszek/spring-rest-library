package com.kodilla.library.service;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.enums.RentalStatus;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public Optional<BookCopy> getBookCopy(final Long id) {
        return bookCopyRepository.findById(id);
    }

    public BookCopy addBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy addBookCopy(BookCopy bookCopy, Long bookIdToUpdate) throws NotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookIdToUpdate);
        BookCopy updatedBookCopy = null;
        if (optionalBook.isPresent()) {
            bookCopy.setBook(optionalBook.get());
            updatedBookCopy = bookCopyRepository.save(bookCopy);
        } else {
            throw new NotFoundException("No such book found.");
        }

        return updatedBookCopy;
    }

    public void deleteBookCopy(final Long id) {
        bookCopyRepository.delete(id);
    }

    public void markBookStatus(Long id, RentalStatus rentalStatus) {
        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(id);

        if(optionalBookCopy.isPresent()) {
            BookCopy bookCopyToUpdate = optionalBookCopy.get();
            bookCopyToUpdate.setRentalStatus(rentalStatus);
            bookCopyRepository.save(bookCopyToUpdate);
        }
    }

    public Long checkNumberOfCopiesOfBookWithStatus(Long id, RentalStatus status) {
        return bookCopyRepository.countAllByBookIdAndRentalStatus(id, status);
    }
}
