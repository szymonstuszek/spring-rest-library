package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.dto.BookCopyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getRentalStatus(),
                null
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getRentalStatus(),
                bookCopy.getBook().getId()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(c -> new BookCopyDto(
                        c.getId(),
                        c.getRentalStatus(),
                        c.getBook().getId())
                )
                .collect(Collectors.toList());
    }
}
