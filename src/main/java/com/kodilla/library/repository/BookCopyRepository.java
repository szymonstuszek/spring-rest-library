package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.RentalStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();

    List<BookCopy> findAllByBookId(Long bookId);

    @Override
    BookCopy save(BookCopy bookCopy);

    @Override
    void delete(Long id);

    Optional<BookCopy> findById(Long id);

    Long countAllByBookId(Long bookId);

    Long countAllByRentalStatus(RentalStatus status);

    Long countAllByBookIdAndRentalStatus(Long id, RentalStatus status);

}
