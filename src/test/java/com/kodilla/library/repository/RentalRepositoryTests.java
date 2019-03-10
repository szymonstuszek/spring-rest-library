package com.kodilla.library.repository;

import com.kodilla.library.domain.Rental;
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

    @Test
    public void testSaveRental() {
        //Given
        Rental rental = new Rental(
                5L,
                5L,
                5L,
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2001, 1, 1),
                LocalDate.of(2002, 1, 1)
        );

        //When
        rentalRepository.save(rental);

        //Then
    }

}
