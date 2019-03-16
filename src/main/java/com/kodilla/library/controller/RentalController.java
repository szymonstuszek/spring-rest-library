package com.kodilla.library.controller;

import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.RentalStatus;
import com.kodilla.library.domain.dto.RentalDto;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.exception.RentalNotFoundException;
import com.kodilla.library.exception.RentalNotPossibleException;
import com.kodilla.library.exception.UserNotFoundException;
import com.kodilla.library.mapper.RentalMapper;
import com.kodilla.library.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/library")
public class RentalController {

    @Autowired
    private RentalMapper rentalMapper;

    @Autowired
    private RentalService rentalService;

    @RequestMapping(method = RequestMethod.GET, value = "rentals")
    public List<RentalDto> getRentals() {
        return rentalMapper.mapToRentalDtoList(rentalService.getAllRentals());
    }

    @RequestMapping(method = RequestMethod.GET, value = "rental")
    public RentalDto getRental(@RequestParam Long id) throws RentalNotFoundException {
        return rentalMapper.mapToRentalDto(rentalService.getRental(id)
                .orElseThrow(RentalNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "rental")
    public void deleteRental(@RequestParam Long id) {
        rentalService.deleteRental(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "rental")
    public RentalDto updateRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        return rentalMapper.mapToRentalDto(rentalService.updateRental(rental));
    }

    //void or RentalDto?
    @RequestMapping(method = RequestMethod.POST, value = "rentals")
    public RentalDto rentBook(
            @RequestParam Long userId,
            @RequestParam Long bookCopyId)
            throws UserNotFoundException, BookCopyNotFoundException, RentalNotPossibleException {

        Rental rental = rentalService.addRental(userId, bookCopyId);

        if(rental != null) {
            return rentalMapper.mapToRentalDto(rental);
        } else {
            throw new RentalNotPossibleException();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "rental-return")
    public RentalDto returnBook(
            @RequestParam Long rentalId) throws RentalNotFoundException {
        Rental finishedRental = rentalService.returnBook(rentalId);

        if(finishedRental != null) {
            return rentalMapper.mapToRentalDto(finishedRental);
        } else {
            throw new RentalNotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "rental-damaged")
    public RentalDto returnDamagedBook(
            @RequestParam Long rentalId) throws RentalNotFoundException {
        Rental finishedRental = rentalService.returnDamagedBook(rentalId);

        if(finishedRental != null) {
            return rentalMapper.mapToRentalDto(finishedRental);
        } else {
            throw new RentalNotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "rental-lost")
    public RentalDto bookHasBeenLost(
            @RequestParam Long rentalId) throws RentalNotFoundException {
        Rental finishedRental = rentalService.bookHasBeenLost(rentalId);

        if(finishedRental != null) {
            return rentalMapper.mapToRentalDto(finishedRental);
        } else {
            throw new RentalNotFoundException();
        }
    }
}
