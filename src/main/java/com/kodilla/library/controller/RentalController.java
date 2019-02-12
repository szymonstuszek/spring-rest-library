package com.kodilla.library.controller;

import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.RentalDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RentalController {

    public List<RentalDto> getRentals() {
        return new ArrayList<>();
    }

    public Rental getRental(Long id) {
        return new Rental();
    }

    public void deleteRental(Long id) {

    }

    public RentalDto updateRenatl(RentalDto rentalDto) {
        return new RentalDto();
    }

    public void createRental(RentalDto RentalDto) {

    }
}
