package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
            bookDto.getId(),
            bookDto.getTitle(),
            bookDto.getAuthor(),
            bookDto.getYearOfPublishing()
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getYearOfPublishing()
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(b -> new BookDto(b.getTitle(), b.getAuthor(), b.getYearOfPublishing()))
                .collect(Collectors.toList());
    }
}
