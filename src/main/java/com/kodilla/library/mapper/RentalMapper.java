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

    //is this ok??
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    public Rental mapToRental(final RentalDto rentalDto) {
        Long userId = rentalDto.getUserId();
        Long bookCopyId = rentalDto.getBookCopyId();

        User retrievedUser = userRepository.findOne(userId);
        BookCopy retrievedCopy = bookCopyRepository.findOne(bookCopyId);

        return new Rental(
                rentalDto.getId(),
                rentalDto.getDateOfRental(),
                rentalDto.getDateOfReturn(),
                rentalDto.getDueOnDate(),
                retrievedUser,
                retrievedCopy
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
