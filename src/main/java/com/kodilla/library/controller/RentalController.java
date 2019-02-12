package com.kodilla.library.controller;

import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.dto.RentalDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class RentalController {

    @RequestMapping(method = RequestMethod.GET, value = "getRentals")
    public List<RentalDto> getRentals() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRental")
    public Rental getRental(@RequestParam Long id) {
        return new Rental();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteRental")
    public void deleteRental(@RequestParam Long id) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateRental")
    public RentalDto updateRental(@RequestBody RentalDto rentalDto) {
        return new RentalDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createRental")
    public void createRental(@RequestBody RentalDto RentalDto) {

    }
}
