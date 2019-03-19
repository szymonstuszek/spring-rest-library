package com.kodilla.library.service;

import com.kodilla.library.domain.User;
import com.kodilla.library.exception.UserNotFoundException;
import com.kodilla.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }

    public User addUser(final User User) {
        return userRepository.save(User);
    }

    public void deleteUser(final Long id) {
        userRepository.delete(id);
    }

    public void payPenalties(final Long userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPenaltiesAmount(BigDecimal.ZERO);
            userRepository.save(user);

        } else {
            throw new UserNotFoundException();
        }
    }
}
