package com.kodilla.library.service;

import com.kodilla.library.domain.Rental;
import com.kodilla.library.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRental(final Long id) {
        return rentalRepository.findById(id);
    }

    public Rental addRental(final Rental Rental) {
        return rentalRepository.save(Rental);
    }

    public void deleteRental(final Long id) {
        rentalRepository.delete(id);
    }
}
