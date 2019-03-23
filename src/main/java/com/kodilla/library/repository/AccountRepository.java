package com.kodilla.library.repository;

import com.kodilla.library.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
    Optional<Account> findById(Long id);
    Optional<Account> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
