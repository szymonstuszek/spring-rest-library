package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.RentalStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Test
    public void testCreateBookCopy() {
        //Given
        BookCopy bookCopy = new BookCopy(
                1L, RentalStatus.AVAILABLE, null
        );

        //When
        BookCopy savedCopy = bookCopyRepository.save(bookCopy);

        //Then
        assertEquals(RentalStatus.AVAILABLE, savedCopy.getRentalStatus());
    }

    @Test
    public void testSetBookForBookCopy() {
        //Given
        BookCopy bookCopy = new BookCopy(
                1L, RentalStatus.LOST, null
        );

        BookCopy savedCopy = bookCopyRepository.save(bookCopy);
        Long copyId = savedCopy.getId();

        Book book = new Book(
                1L,
                "80 days around the world",
                "Harlequin",2005
        );

        BookCopy retrievedCopy = bookCopyRepository.findOne(copyId);

        book.getBookCopies().add(retrievedCopy);
        retrievedCopy.setBook(book);

        //When
        Book savedBookWithACopy = bookRepository.save(book);

        //Then
        assertEquals(1, savedBookWithACopy.getBookCopies().size());
    }
}
