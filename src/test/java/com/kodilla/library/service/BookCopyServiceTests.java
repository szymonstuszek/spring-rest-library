package com.kodilla.library.service;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.RentalStatus;
import com.kodilla.library.repository.BookCopyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyServiceTests {

    @Autowired
    private BookCopyService bookCopyService;

    @Autowired
    private BookCopyRepository bookCopyRepository;


    @Test
    public void testChangeBookCopyStatusToAvailable() {
        //Given
        BookCopy bookCopy = new BookCopy(
                500L, RentalStatus.LOST, null
        );

        BookCopy savedCopy = bookCopyRepository.save(bookCopy);
        Long copyId = savedCopy.getId();

        //When
        bookCopyService.markBookAsAvailable(copyId);
        BookCopy updatedBook = bookCopyRepository.findOne(copyId);

        //Then
        assertEquals(updatedBook.getRentalStatus(), RentalStatus.AVAILABLE);
    }

    @Test
    public void testChangeBookCopyStatusToLost() {
        //Given
        BookCopy bookCopy = new BookCopy(
                500L, RentalStatus.AVAILABLE, null
        );

        BookCopy savedCopy = bookCopyRepository.save(bookCopy);
        Long copyId = savedCopy.getId();

        //When
        bookCopyService.markBookAsLost(copyId);
        BookCopy updatedBook = bookCopyRepository.findOne(copyId);

        //Then
        assertEquals(updatedBook.getRentalStatus(), RentalStatus.LOST);
    }

    @Test
    public void testChangeBookCopyStatusToDamaged() {
        //Given
        BookCopy bookCopy = new BookCopy(
                500L, RentalStatus.LOST, null
        );

        BookCopy savedCopy = bookCopyRepository.save(bookCopy);
        Long copyId = savedCopy.getId();

        //When
        bookCopyService.markBookAsDamaged(copyId);
        BookCopy updatedBook = bookCopyRepository.findOne(copyId);

        //Then
        assertEquals(updatedBook.getRentalStatus(), RentalStatus.DAMAGED);
    }

    @Test
    public void testChangeBookCopyStatusToRented() {
        //Given
        BookCopy bookCopy = new BookCopy(
                500L, RentalStatus.LOST, null
        );

        BookCopy savedCopy = bookCopyRepository.save(bookCopy);
        Long copyId = savedCopy.getId();

        //When
        bookCopyService.markBookAsRented(copyId);
        BookCopy updatedBook = bookCopyRepository.findOne(copyId);

        //Then
        assertEquals(updatedBook.getRentalStatus(), RentalStatus.RENTED);
    }

    @Test
    public void countBookCopiesAvailableToRent() {
        //Given
        //When
        Long count = bookCopyService.checkNumberOfCopiesOfBookWithStatus(1L, RentalStatus.AVAILABLE);

        //Then
        assertTrue(count > 0);
    }
}
