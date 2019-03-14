package com.kodilla.library.repository;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.RentalStatus;
import com.kodilla.library.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalRepositoryTests {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Test
    public void testSaveRental() {
        //Given
        BookCopy bookCopy = new BookCopy(1L, RentalStatus.AVAILABLE, null);
        User user = new User(5L, "Greg", "Downhill", LocalDate.now());
        Rental rental = new Rental(
                            1L,
                            LocalDate.now(),
                            null,
                            LocalDate.of(2019, 3,24)
        );
        rental.setBookCopy(bookCopy);
        rental.setUser(user);

        user.getRentals().add(rental);
        bookCopy.getRentals().add(rental);

        //When
        rentalRepository.save(rental);

        //Then
    }

}
