package com.kodilla.library.controller;

import com.kodilla.library.domain.User;
import com.kodilla.library.domain.UserDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    public List<UserDto> getUsers() {
        return new ArrayList<>();
    }

    public UserDto getUser(Long id) {
        return new UserDto();
    }

    public void deleteUser(Long id) {

    }

    public UserDto updateUser(UserDto userDto) {
        return new UserDto();
    }

    public void createUser(UserDto userDto) {

    }
}
