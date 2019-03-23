package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.RentalDto;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {

    public Rental mapToRental(final RentalDto rentalDto) {
        return new Rental(
                rentalDto.getId(),
                rentalDto.getDateOfRental(),
                rentalDto.getDateOfReturn(),
                rentalDto.getDueOnDate(),
                null,
                null
        );
    }

    public RentalDto mapToRentalDto(final Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getDateOfRental(),
                rental.getDateOfReturn(),
                rental.getDueOnDate(),
                rental.getUser().getId(),
                rental.getBookCopy().getId()
        );
    }

    public List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList) {
        return rentalList.stream()
                .map(r -> new RentalDto(
                            r.getId(),
                            r.getDateOfRental(),
                            r.getDateOfReturn(),
                            r.getDueOnDate(),
                            r.getUser().getId(),
                            r.getBookCopy().getId()
                ))
                .collect(Collectors.toList());
    }
}
