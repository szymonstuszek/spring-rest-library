package com.kodilla.library.service;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.enums.RentalStatus;
import com.kodilla.library.domain.User;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.exception.RentalNotFoundException;
import com.kodilla.library.exception.UserNotFoundException;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.RentalRepository;
import com.kodilla.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.kodilla.library.constants.LibraryConstants.*;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookCopyService bookCopyService;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRental(final Long id) {
        return rentalRepository.findById(id);
    }

    public Rental updateRental(final Rental Rental) {
        return rentalRepository.save(Rental);
    }

    public void deleteRental(final Long id) {
        rentalRepository.delete(id);
    }

    public Rental addRental(final Long userId, final Long bookCopyId) throws
            UserNotFoundException, BookCopyNotFoundException {

        Rental rental = null;
        if(isAccountActive(userId) & isBookAvailable(bookCopyId)) {
            Rental rentalToSave = createRental(userId, bookCopyId);
            rental = rentalRepository.save(rentalToSave);
            bookCopyService.markBookStatus(bookCopyId, RentalStatus.RENTED);
        }

        return rental;
    }

    private Rental createRental(Long userId, Long bookCopyId) {
        BookCopy bookCopy = new BookCopy();
        User user = new User();

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        }

        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(bookCopyId);
        if(optionalBookCopy.isPresent()) {
            bookCopy = optionalBookCopy.get();
        }

        return new Rental(
                LocalDate.now(),
                null,
                LocalDate.now().plusDays(DAYS_TO_RETURN_BOOK),
                user,
                bookCopy
        );
    }

    private boolean isBookAvailable(Long bookId) throws BookCopyNotFoundException {
        Optional<BookCopy> bookCopy = bookCopyRepository.findById(bookId);

        if(bookCopy.isPresent()) {
            return bookCopy.get().getRentalStatus().equals(RentalStatus.AVAILABLE);
        } else {
            throw new BookCopyNotFoundException();
        }
    }

    private boolean isAccountActive(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return user.get().isActive();
        } else {
            throw new UserNotFoundException();
        }
    }

    public Rental returnBook(Long rentalId) throws RentalNotFoundException {
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);

        Rental finishedRental;
        if (optionalRental.isPresent()) {
            finishedRental = optionalRental.get();
            finishedRental.setDateOfReturn(LocalDate.now());
            rentalRepository.save(finishedRental);

            if (hasToPayOverduePenalties(finishedRental)) {
                applyOverduePenalties(finishedRental);
            }

            Long returnedBookCopyId = finishedRental.getBookCopy().getId();
            bookCopyService.markBookStatus(returnedBookCopyId, RentalStatus.AVAILABLE);

        } else {
            throw new RentalNotFoundException();
        }

        return finishedRental;
    }

    private boolean hasToPayOverduePenalties(Rental rental) {
        LocalDate dateOfReturn = rental.getDateOfReturn();
        return dateOfReturn.isAfter(rental.getDueOnDate());
    }

    private double calculatePenalties(Rental rental) {
        long daysOverdue = DAYS.between(
                rental.getDueOnDate(),
                rental.getDateOfReturn()
        );

        return (double) daysOverdue * PENALTY_PER_DAY_OVERDUE;
    }

    private void applyOverduePenalties(Rental rental) {
        double penalties = calculatePenalties(rental);
        Long userId = rental.getUser().getId();
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            double currentPenaltiesOfUser = user.getPenaltiesAmount();
            user.setPenaltiesAmount(currentPenaltiesOfUser + penalties);
            userRepository.save(user);
        }
    }

    private void payPenalty(double amount, Rental rental, RentalStatus status) {
        Long userId = rental.getUser().getId();
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            double penaltiesAmount = user.getPenaltiesAmount();
            user.setPenaltiesAmount(penaltiesAmount + amount);
            userRepository.save(user);

            Long damagedBookId = rental.getBookCopy().getId();
            bookCopyService.markBookStatus(damagedBookId, status);
        }
    }

    public Rental returnDamagedBook(Long rentalId) throws RentalNotFoundException {
        Rental finishedRental = returnBook(rentalId);
        payPenalty(PENALTY_FOR_DAMAGING_A_BOOK, finishedRental, RentalStatus.DAMAGED);
        return finishedRental;
    }

    public Rental bookHasBeenLost(Long rentalId) throws RentalNotFoundException {
        Rental finishedRental = returnBook(rentalId);
        payPenalty(PENALTY_FOR_LOSING_A_BOOK, finishedRental, RentalStatus.LOST);
        return finishedRental;
    }
}
