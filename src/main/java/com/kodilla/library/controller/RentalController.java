package com.kodilla.library.controller;

import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.RentalStatus;
import com.kodilla.library.domain.dto.RentalDto;
import com.kodilla.library.exception.RentalNotFoundException;
import com.kodilla.library.mapper.RentalMapper;
import com.kodilla.library.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public RentalDto getRental(@RequestParam Long id) throws RentalNotFoundException{
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
        return rentalMapper.mapToRentalDto(rentalService.addRental(rental));
    }

    @RequestMapping(method = RequestMethod.POST, value = "rentals")
    public void createRental(@RequestBody RentalDto rentalDto) {
        rentalService.addRental(rentalMapper.mapToRental(rentalDto));
    }
}
