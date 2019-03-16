package com.kodilla.library.service;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.RentalStatus;
import com.kodilla.library.domain.User;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.exception.UserNotFoundException;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.RentalRepository;
import com.kodilla.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRental(final Long id) {
        return rentalRepository.findById(id);
    }

    public Rental addRental(final Long userId, final Long bookCopyId) throws
            UserNotFoundException, BookCopyNotFoundException {

        Rental rental = null;
        if(isAccountActive(userId) & isBookAvailable(bookCopyId)) {
            Rental rentalToSave = createRental(userId, bookCopyId);
            rental = rentalRepository.save(rentalToSave);
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
                1L,
                LocalDate.now(),
                null,
                LocalDate.now().plusDays(14),
                user,
                bookCopy
        );
    }

    public void deleteRental(final Long id) {
        rentalRepository.delete(id);
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

    public Rental updateRental(final Rental Rental) {
        return rentalRepository.save(Rental);
    }
}
