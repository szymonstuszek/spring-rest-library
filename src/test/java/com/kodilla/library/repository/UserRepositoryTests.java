package com.kodilla.library.repository;

import com.kodilla.library.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        //Given
        User user = new User("Michael", "Jackson", LocalDate.now(), true);

        //When
        User savedUser = userRepository.save(user);

        //Then
        assertEquals("Michael", savedUser.getFirstName());
    }

    @Test
    public void testReadUser() {
        //Given
        User user = new User(1L, "John", "Doe", LocalDate.now(), true);
        User savedUser = userRepository.save(user);

        //When
        Long userId = savedUser.getId();
        User retrievedUser = userRepository.findOne(userId);

        //Then
        assertEquals("John", retrievedUser.getFirstName());
    }

    @Test
    public void testUpdateUser() {
        //Given
        User user = new User(1L, "John", "Doe", LocalDate.now(), true);
        User savedUser = userRepository.save(user);

        //When
        Long userId = savedUser.getId();
        User retrievedUser = userRepository.findOne(userId);

        retrievedUser.setFirstName("Marcus");
        userRepository.save(retrievedUser);

        User userAfterUpdate = userRepository.findOne(userId);

        //Then
        assertEquals("Marcus", userAfterUpdate.getFirstName());
    }

    @Test
    public void testDeleteUser() {
        //Given
        User user = new User(1L, "Michael", "Doeglas", LocalDate.now(), true);

        //When
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        //Then
        userRepository.delete(userId);
    }
}
