package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.domain.enums.RentalStatus;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.exception.RentalNotFoundException;
import com.kodilla.library.exception.UserNotFoundException;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.BookRepository;
import com.kodilla.library.repository.RentalRepository;
import com.kodilla.library.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static com.kodilla.library.constants.LibraryConstants.DAYS_TO_RETURN_BOOK;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalServiceTests {

    private Random random = new Random();

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;


    //testing with postman creates no error
    //changed to merge
    @Test
    public void testCreateRental() throws UserNotFoundException, BookCopyNotFoundException {
        //Given
        User user = new User(
                "Stan" + random.nextInt(1000),
                "Oxford",
                LocalDate.now().minusDays(100),
                true
        );

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        Book book = new Book("Real Biography of Robin Hood" + random.nextInt(1000), "John Snow", 2018);

        for (int i = 0; i < 3; i++) {
            BookCopy bookCopy = new BookCopy(RentalStatus.AVAILABLE, book);
            book.getBookCopies().add(bookCopy);
        }

        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();

        List<BookCopy> bookCopies = bookCopyRepository.findAllByBookId(bookId);
        BookCopy copyToRent = bookCopies.get(0);
        Long copyToRentId = copyToRent.getId();

        //When
        Rental rental = rentalService.addRental(userId, copyToRentId);
        BookCopy copyAfterRenting = bookCopyRepository.findOne(copyToRentId);

        //Then
        assertEquals(copyAfterRenting.getRentalStatus(), RentalStatus.RENTED);
        assertEquals(rental.getUser().getId(), user.getId());
        assertEquals(rental.getBookCopy().getId(), copyAfterRenting.getId());
    }

    @Test
    public void testCreateRentalWhenBookNotAvailable() throws UserNotFoundException, BookCopyNotFoundException  {
        //Given
        User user = new User(
                "Jordne" + random.nextInt(1000),
                "Blefor",
                LocalDate.now().minusDays(35),
                true
        );

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        Book book = new Book("Maze of labyrynth" + random.nextInt(1000), "Michael Jackoson", 2002);

        for (int i = 0; i < 2; i++) {
            BookCopy bookCopy = new BookCopy(RentalStatus.RENTED, book);
            book.getBookCopies().add(bookCopy);
        }

        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();

        List<BookCopy> bookCopies = bookCopyRepository.findAllByBookId(bookId);
        BookCopy copyToRent = bookCopies.get(0);
        Long copyToRentId = copyToRent.getId();

        //When
        Rental rental = rentalService.addRental(userId, copyToRentId);

        //Then
        assertNull(rental);
    }

    @Test
    public void testCreateRentalWhenUserIsLocked() throws UserNotFoundException, BookCopyNotFoundException {
        //Given
        User user = new User(
                "Donald" + random.nextInt(1000),
                "Duck",
                LocalDate.now().minusDays(22),
                false
        );

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        Book book = new Book("Golden Age of Palo Alto" + random.nextInt(1000), "Marcus Ford", 2006);

        for (int i = 0; i < 2; i++) {
            BookCopy bookCopy = new BookCopy(RentalStatus.AVAILABLE, book);
            book.getBookCopies().add(bookCopy);
        }

        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();

        List<BookCopy> bookCopies = bookCopyRepository.findAllByBookId(bookId);
        BookCopy copyToRent = bookCopies.get(0);
        Long copyToRentId = copyToRent.getId();

        //When
        Rental rental = rentalService.addRental(userId, copyToRentId);

        //Then
        assertNull(rental);
    }

    @Test(expected = UserNotFoundException.class)
    public void testCreateRentalWhenUserIdDoesNotExist() throws UserNotFoundException, BookCopyNotFoundException {
        //Given
        Book book = new Book("Story of smurpghs" + random.nextInt(1000), "Winston Churcil", 2006);

        for (int i = 0; i < 2; i++) {
            BookCopy bookCopy = new BookCopy(RentalStatus.AVAILABLE, book);
            book.getBookCopies().add(bookCopy);
        }

        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();

        List<BookCopy> bookCopies = bookCopyRepository.findAllByBookId(bookId);
        BookCopy copyToRent = bookCopies.get(0);
        Long copyToRentId = copyToRent.getId();

        //When & Then
        rentalService.addRental(123456789L, copyToRentId);
    }

    @Test(expected = BookCopyNotFoundException.class)
    public void testCreateRentalWhenBookDoesNotExist() throws UserNotFoundException, BookCopyNotFoundException {
        //Given
        User user = new User(
                "Loanardo" + random.nextInt(1000),
                "Di carpio",
                LocalDate.now().minusDays(22),
                false
        );

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        //When & Then
        rentalService.addRental(userId, 123456797L);
    }

    @Test
    public void testReturnBook() throws UserNotFoundException, BookCopyNotFoundException, RentalNotFoundException {
        //Given
        User user = new User(
                "Ibrahim" + random.nextInt(1000),
                "Oxford",
                LocalDate.now().minusDays(100),
                true
        );

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        Book book = new Book("Java guide" + random.nextInt(1000), "Neal Armstrong", 2004);

        for (int i = 0; i < 3; i++) {
            BookCopy bookCopy = new BookCopy(RentalStatus.AVAILABLE, book);
            book.getBookCopies().add(bookCopy);
        }

        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();

        List<BookCopy> bookCopies = bookCopyRepository.findAllByBookId(bookId);
        BookCopy copyToRent = bookCopies.get(0);
        Long copyToRentId = copyToRent.getId();

        Rental rental = rentalService.addRental(userId, copyToRentId);
        Long rentalId = rental.getId();
        //When
        Rental finishedRental = rentalService.returnBook(rentalId);

        Long returnedBookCopyId = finishedRental.getBookCopy().getId();
        BookCopy returnedBookCopy = bookCopyRepository.findOne(returnedBookCopyId);

        //Then
        assertEquals(finishedRental.getDateOfReturn(), LocalDate.now());
        assertEquals(returnedBookCopy.getRentalStatus(), RentalStatus.AVAILABLE);
    }

    @Test(expected = RentalNotFoundException.class)
    public void testReturnBookWhenRentalDoesNotExist() throws RentalNotFoundException {
        //Given
        //When
        //Then
        Rental finishedRental = rentalService.returnBook(12345678L);
    }

    @Test
    public void testReturnDamagedBook() throws UserNotFoundException, BookCopyNotFoundException, RentalNotFoundException {
        //Given
        User user = new User(
                "Britney" + random.nextInt(1000),
                "Spears",
                LocalDate.now().minusDays(100),
                true
        );

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        Book book = new Book("How to sing." + random.nextInt(1000), "Paelo Coehhlo", 2004);

        for (int i = 0; i < 4; i++) {
            BookCopy bookCopy = new BookCopy(RentalStatus.AVAILABLE, book);
            book.getBookCopies().add(bookCopy);
        }

        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();

        List<BookCopy> bookCopies = bookCopyRepository.findAllByBookId(bookId);
        BookCopy copyToRent = bookCopies.get(0);
        Long copyToRentId = copyToRent.getId();

        Rental rental = rentalService.addRental(userId, copyToRentId);
        Long rentalId = rental.getId();

        //When
        Rental finishedRental = rentalService.returnDamagedBook(rentalId);

        Long returnedBookCopyId = finishedRental.getBookCopy().getId();
        BookCopy returnedBookCopy = bookCopyRepository.findOne(returnedBookCopyId);

        User userThatHasToPayPenalty = userRepository.findOne(userId);

        //Then
        assertEquals(finishedRental.getDateOfReturn(), LocalDate.now());
        assertEquals(returnedBookCopy.getRentalStatus(), RentalStatus.DAMAGED);
        assertTrue(userThatHasToPayPenalty.getPenaltiesAmount()
                .compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    public void testBookHasBeenLost() throws UserNotFoundException, BookCopyNotFoundException, RentalNotFoundException {
        //Given
        User user = new User(
                "Johhny" + random.nextInt(1000),
                "Bravo",
                LocalDate.now().minusDays(130),
                true
        );

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        Book book = new Book("Glamorous hairs" + random.nextInt(1000), "Wilhelm Tell", 2018);

        for (int i = 0; i < 1; i++) {
            BookCopy bookCopy = new BookCopy(RentalStatus.AVAILABLE, book);
            book.getBookCopies().add(bookCopy);
        }

        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();

        List<BookCopy> bookCopies = bookCopyRepository.findAllByBookId(bookId);
        BookCopy copyToRent = bookCopies.get(0);
        Long copyToRentId = copyToRent.getId();

        Rental rental = rentalService.addRental(userId, copyToRentId);
        Long rentalId = rental.getId();

        //When
        Rental finishedRental = rentalService.bookHasBeenLost(rentalId);
        User userThatLostTheBook = userRepository.findOne(userId);
        BookCopy lostBookCopy = bookCopyRepository.findOne(copyToRentId);

        //Then
        assertTrue(userThatLostTheBook.getPenaltiesAmount()
                .compareTo(BigDecimal.valueOf(15.0)) >= 0 );
        assertEquals(lostBookCopy.getRentalStatus(), RentalStatus.LOST);
    }

    @Test
    public void testHasToPayPenalties() {
        //Given
        Rental rentalOnTime =  new Rental(
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                LocalDate.now().plusDays(DAYS_TO_RETURN_BOOK),
                new User(),
                new BookCopy()
        );

        Rental rentalOverDue =  new Rental(
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                LocalDate.now().plusDays(DAYS_TO_RETURN_BOOK),
                new User(),
                new BookCopy()
        );

        LocalDate dateOfReturn1 = rentalOnTime.getDateOfReturn();
        LocalDate dateOfReturn2 = rentalOverDue.getDateOfReturn();

        //When & Then
        assertFalse(dateOfReturn1.isAfter(rentalOnTime.getDueOnDate()));
        assertTrue(dateOfReturn2.isAfter(rentalOnTime.getDueOnDate()));
    }

    @Test
    public void testHasToPayPenaltiesOnDateOverdue() {
        //Given
        Rental rentalOnDueDate =  new Rental(
                LocalDate.now(),
                LocalDate.now().plusDays(14),
                LocalDate.now().plusDays(DAYS_TO_RETURN_BOOK),
                new User(),
                new BookCopy()
        );
        LocalDate dateOfReturn = rentalOnDueDate.getDateOfReturn();

        //When & Then
        assertFalse(dateOfReturn.isAfter(rentalOnDueDate.getDueOnDate()));
    }

    @Test
    public void testCalculatePenalties() {
        //Given
        Rental rentalOverDue1 =  new Rental(
                LocalDate.now(),
                LocalDate.now().plusDays(15),
                LocalDate.now().plusDays(DAYS_TO_RETURN_BOOK),
                new User(),
                new BookCopy()
        );

        Rental rentalOverDue2 =  new Rental(
                LocalDate.now(),
                LocalDate.now().plusDays(20),
                LocalDate.now().plusDays(DAYS_TO_RETURN_BOOK),
                new User(),
                new BookCopy()
        );

        long daysOverdue1 = DAYS.between(
                rentalOverDue1.getDueOnDate(),
               rentalOverDue1.getDateOfReturn()
        );

        long daysOverdue2 = DAYS.between(
                rentalOverDue2.getDueOnDate(),
                rentalOverDue2.getDateOfReturn()
        );

        assertTrue(daysOverdue1 == 1);
        assertTrue(daysOverdue2 == 6);
    }

}
