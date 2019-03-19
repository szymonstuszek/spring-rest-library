package com.kodilla.library.service;

import com.kodilla.library.domain.User;
import com.kodilla.library.exception.UserNotFoundException;
import com.kodilla.library.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testPayPenalties() throws UserNotFoundException {
        //Given
        User user = new User(
                "Bruace",
                "Willis",
                LocalDate.now().minusDays(36),
                true
        );

        user.setPenaltiesAmount(BigDecimal.valueOf(30));

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        //When
        userService.payPenalties(userId);

        User userAfterPayingPenalties = userRepository.findOne(userId);

        //Then
        assertTrue(userAfterPayingPenalties.getPenaltiesAmount()
                .compareTo(BigDecimal.ZERO) == 0);
    }
}
