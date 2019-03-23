package com.kodilla.library.service;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.enums.RentalStatus;
import com.kodilla.library.domain.User;
import com.kodilla.library.exception.*;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.RentalRepository;
import com.kodilla.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public Rental updateRental(final Rental rental, Long userId, Long bookCopyId)
            throws UserNotActiveException , BookCopyNotAvailableException {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()) {
            User userToUpdate = optionalUser.get();
            if(userToUpdate.isActive()) {
                rental.setUser(userToUpdate);
            } else {
                throw new UserNotActiveException();
            }
        }

        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(bookCopyId);

        if(optionalBookCopy.isPresent()) {
            BookCopy bookCopyToUpdate = optionalBookCopy.get();
            //book copy is not in an active rental
            if(bookCopyToUpdate.getRentalStatus().equals(RentalStatus.AVAILABLE)) {
                rental.setBookCopy(bookCopyToUpdate);
            } else {
                throw new BookCopyNotAvailableException();
            }
        }

        return rentalRepository.save(rental);
    }

    public void deleteRental(final Long id) {
        rentalRepository.delete(id);
    }

    public Rental addRental(final Long userId, final Long bookCopyId) throws
            NotFoundException, NotFoundException {

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

    private boolean isBookAvailable(Long bookId) throws NotFoundException {
        Optional<BookCopy> bookCopy = bookCopyRepository.findById(bookId);

        if(bookCopy.isPresent()) {
            return bookCopy.get().getRentalStatus().equals(RentalStatus.AVAILABLE);
        } else {
            throw new NotFoundException();
        }
    }

    private boolean isAccountActive(Long userId) throws NotFoundException {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return user.get().isActive();
        } else {
            throw new NotFoundException();
        }
    }

    public Rental returnBook(Long rentalId) throws NotFoundException {
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
            throw new NotFoundException();
        }

        return finishedRental;
    }

    private boolean hasToPayOverduePenalties(Rental rental) {
        LocalDate dateOfReturn = rental.getDateOfReturn();
        return dateOfReturn.isAfter(rental.getDueOnDate());
    }

    private BigDecimal calculatePenalties(Rental rental) {
        long daysOverdue = DAYS.between(
                rental.getDueOnDate(),
                rental.getDateOfReturn()
        );

        return PENALTY_PER_DAY_OVERDUE.multiply(BigDecimal.valueOf(daysOverdue));
    }

    private void applyOverduePenalties(Rental rental) {
        BigDecimal penalties = calculatePenalties(rental);
        Long userId = rental.getUser().getId();
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BigDecimal currentPenaltiesOfUser = user.getPenaltiesAmount();
            user.setPenaltiesAmount(currentPenaltiesOfUser.add(penalties));
            userRepository.save(user);
        }
    }

    private void payPenalty(BigDecimal amount, Rental rental, RentalStatus status) {
        Long userId = rental.getUser().getId();
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            BigDecimal penaltiesAmount = user.getPenaltiesAmount();
            user.setPenaltiesAmount(penaltiesAmount.add(amount));
            userRepository.save(user);

            Long damagedBookId = rental.getBookCopy().getId();
            bookCopyService.markBookStatus(damagedBookId, status);
        }
    }

    public Rental returnDamagedBook(Long rentalId) throws NotFoundException {
        Rental finishedRental = returnBook(rentalId);
        payPenalty(PENALTY_FOR_DAMAGING_A_BOOK, finishedRental, RentalStatus.DAMAGED);
        return finishedRental;
    }

    public Rental bookHasBeenLost(Long rentalId) throws NotFoundException {
        Rental finishedRental = returnBook(rentalId);
        payPenalty(PENALTY_FOR_LOSING_A_BOOK, finishedRental, RentalStatus.LOST);
        return finishedRental;
    }
}
