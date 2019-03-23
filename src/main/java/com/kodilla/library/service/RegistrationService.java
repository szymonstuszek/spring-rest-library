package com.kodilla.library.service;

import com.kodilla.library.domain.Account;
import com.kodilla.library.domain.User;
import com.kodilla.library.repository.AccountRepository;
import com.kodilla.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public void setUserForAccount(Long accountId, Long userId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Account accountToSetup = null;

        if (optionalAccount.isPresent()) {
            accountToSetup = optionalAccount.get();
            if (isNoUserAssigned(accountToSetup)) {
                assignUser(userId, accountToSetup);
                accountRepository.save(accountToSetup);
            }
        }
    }

    private void assignUser(Long userId, Account accountToSetup) {
        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.ifPresent(accountToSetup::setUser);
    }

    private boolean isNoUserAssigned(Account account) {
        return account.getUser() == null;
    }
}
