package com.kodilla.library.controller;

import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.dto.RentalDto;
import com.kodilla.library.exception.*;
import com.kodilla.library.mapper.RentalMapper;
import com.kodilla.library.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/library",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
)
@CrossOrigin("*")
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
    public RentalDto getRental(@RequestParam Long id) throws NotFoundException {
        return rentalMapper.mapToRentalDto(rentalService.getRental(id)
                .orElseThrow(NotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "rentals")
    public void deleteRental(@RequestParam Long id) {
        rentalService.deleteRental(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "rentals")
    public RentalDto updateRental(@RequestBody RentalDto rentalDto)
            throws UserNotActiveException, BookCopyNotAvailableException {
        Rental rental = rentalMapper.mapToRental(rentalDto);

        Long userId = rentalDto.getUserId();
        Long bookCopyId = rentalDto.getBookCopyId();
        Rental updatedRental = rentalService.updateRental(rental, userId, bookCopyId);

        return rentalMapper.mapToRentalDto(updatedRental);
    }

    @RequestMapping(method = RequestMethod.POST, value = "rentals")
    public RentalDto rentBook(
            @RequestParam Long userId,
            @RequestParam Long bookCopyId)
            throws NotFoundException, RentalNotPossibleException {

        Rental rental = rentalService.addRental(userId, bookCopyId);

        if(rental != null) {
            return rentalMapper.mapToRentalDto(rental);
        } else {
            throw new RentalNotPossibleException();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "rentals/return/{rentalId}")
    public RentalDto returnBook(
            @PathVariable("rentalId") Long rentalId) throws NotFoundException {
        Rental finishedRental = rentalService.returnBook(rentalId);

        if(finishedRental != null) {
            return rentalMapper.mapToRentalDto(finishedRental);
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "rentals/damaged/{rentalId}")
    public RentalDto returnDamagedBook(
            @PathVariable("rentalId") Long rentalId) throws NotFoundException {
        Rental finishedRental = rentalService.returnDamagedBook(rentalId);

        if(finishedRental != null) {
            return rentalMapper.mapToRentalDto(finishedRental);
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "rentals/lost/{rentalId}")
    public RentalDto bookHasBeenLost(
            @PathVariable("rentalId") Long rentalId) throws NotFoundException {
        Rental finishedRental = rentalService.bookHasBeenLost(rentalId);

        if(finishedRental != null) {
            return rentalMapper.mapToRentalDto(finishedRental);
        } else {
            throw new NotFoundException();
        }
    }
}
