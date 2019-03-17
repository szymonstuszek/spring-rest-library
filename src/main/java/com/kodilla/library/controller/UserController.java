package com.kodilla.library.controller;

import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.UserDto;
import com.kodilla.library.exception.UserNotFoundException;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET, value = "users")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "user")
    public UserDto getUser(@RequestParam Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUser(id)
                .orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "user")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "user")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        return userMapper.mapToUserDto(userService.addUser(user));
    }

    @RequestMapping(method = RequestMethod.POST, value = "users")
    public void createUser(@RequestBody UserDto userDto) {
        userService.addUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "user-penalty")
    public void payPenalties(@RequestParam Long userId) throws UserNotFoundException {
        userService.payPenalties(userId);
    }
}
