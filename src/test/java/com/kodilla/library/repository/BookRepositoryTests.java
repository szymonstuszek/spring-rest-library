package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.RentalStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Test
    public void saveBook() {
        //Given
        Book book = new Book(
                1L,
                "Robinson Cruzoe",
                "Daniel Defoe",
                2000
        );
        //When
        Book savedBook = bookRepository.save(book);

        //Then
        assertEquals("Daniel Defoe", savedBook.getAuthor());
    }

    @Test
    public void getBook() {
        //Given
        Book book = new Book(
                1000L,
                "Ace ventuer",
                "Paolo Coehloe",
                2000
        );

        //When
        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();


        Book retrievedBook = bookRepository.findOne(bookId);

        //Then
        assertEquals("Paolo Coehloe", retrievedBook.getAuthor());
    }

    @Test
    public void updateBook() {
        //Given
        Book book = new Book(
                1L,
                "The new Robinson Cruzoe",
                "Not Daniel Defoe",
                2010
        );
        //When
        Book updatedBook = bookRepository.save(book);

        //Then
        assertEquals("Not Daniel Defoe", updatedBook.getAuthor());
    }

    //How to write this when, fetchType is LAZY?
    @Test
    public void updateBookWithANewCopy() {
        //Given
        Book book = new Book(
                1L,
                "Elon Musk",
                "George Clooney",
                2010
        );

        BookCopy bookCopy1 = new BookCopy(1L, RentalStatus.AVAILABLE, book);
        BookCopy bookCopy2 = new BookCopy(2L, RentalStatus.LOST, book);

        book.getBookCopies().add(bookCopy1);
        book.getBookCopies().add(bookCopy2);

        bookCopy1.setBook(book);
        bookCopy2.setBook(book);

        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();


        Book retrievedBook = bookRepository.findOne(bookId);
        BookCopy additionalCopy = new BookCopy(3L, RentalStatus.DAMAGED, retrievedBook);
        retrievedBook.getBookCopies().add(additionalCopy);
        additionalCopy.setBook(retrievedBook);

        bookRepository.save(retrievedBook);

        //Then
        assertEquals(3, retrievedBook.getBookCopies().size());
    }

    @Test
    public void deleteBook() {
        //Given
        Book book = new Book(
                1L,
                "John Snow biography",
                "George Marin",
                2010
        );
        //When
        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();

        bookRepository.delete(bookId);
        Optional<Book> removedBook = bookRepository.findById(bookId);

        //Then
        assertFalse( removedBook.isPresent());
    }

    @Test
    public void saveBookWith2Copies() {
        //Given
        Book book = new Book(
                1L,
                "Robinson Cruzoe",
                "Daniel Defoe",
                2000
        );

        BookCopy bookCopy1 = new BookCopy(1L, RentalStatus.AVAILABLE, book);
        BookCopy bookCopy2 = new BookCopy(2L, RentalStatus.LOST, book);

        book.getBookCopies().add(bookCopy1);
        book.getBookCopies().add(bookCopy2);

        bookCopy1.setBook(book);
        bookCopy2.setBook(book);

        //When
        Book savedBook = bookRepository.save(book);

        //Then
        assertEquals(2, savedBook.getBookCopies().size());
    }

    @Test
    public void deleteBookWith2Copies() {
        //Given
        Book book = new Book(
                1L,
                "Fairy tales",
                "Grimm brothers",
                2000
        );

        BookCopy bookCopy1 = new BookCopy(1L, RentalStatus.AVAILABLE, book);
        BookCopy bookCopy2 = new BookCopy(2L, RentalStatus.LOST, book);

        book.getBookCopies().add(bookCopy1);
        book.getBookCopies().add(bookCopy2);

        bookCopy1.setBook(book);
        bookCopy2.setBook(book);

        //When
        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();

        Long savedBookCopyId = savedBook.getBookCopies().get(0).getId();

        bookRepository.delete(bookId);

        Optional<BookCopy> erasedBookCopy = bookCopyRepository.findById(savedBookCopyId);

        //Then
        assertFalse(erasedBookCopy.isPresent());
    }

}
