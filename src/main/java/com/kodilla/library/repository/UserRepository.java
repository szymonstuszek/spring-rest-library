package com.kodilla.library.repository;

import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    User save(User user);

    @Override
    void delete(Long id);

    Optional<User> findById(Long id);
}
