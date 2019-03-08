package com.kodilla.library.mapper;

import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.dto.RentalDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {

    public Rental mapToRental(final RentalDto rentalDto) {
        return new Rental(
                rentalDto.getId(),
                rentalDto.getBookCopyId(),
                rentalDto.getUserId(),
                rentalDto.getDateOfRental(),
                rentalDto.getDateOfReturn(),
                rentalDto.getDueOnDate()
        );
    }

    public RentalDto mapToRentalDto(final Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getBookCopyId(),
                rental.getUserId(),
                rental.getDateOfRental(),
                rental.getDateOfReturn(),
                rental.getDueOnDate()
        );
    }

    public List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList) {
        return rentalList.stream()
                .map(r -> new RentalDto(
                            r.getId(),
                            r.getBookCopyId(),
                            r.getUserId(),
                            r.getDateOfRental(),
                            r.getDateOfReturn(),
                            r.getDueOnDate()
                ))
                .collect(Collectors.toList());
    }
}
