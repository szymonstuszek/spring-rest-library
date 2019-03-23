package com.kodilla.library.repository;

import com.kodilla.library.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentalRepository extends CrudRepository<Rental, Long> {

    @Override
    List<Rental> findAll();

    @Override
    Rental save(Rental rental);

    @Override
    void delete(Long id);

    Optional<Rental> findById(Long id);

}
