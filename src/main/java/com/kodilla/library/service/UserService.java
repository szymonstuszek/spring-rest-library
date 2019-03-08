package com.kodilla.library.service;

import com.kodilla.library.domain.User;
import com.kodilla.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
