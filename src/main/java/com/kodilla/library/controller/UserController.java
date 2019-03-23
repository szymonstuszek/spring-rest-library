package com.kodilla.library.controller;

import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.UserDto;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/library",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
)
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
    public UserDto getUser(@RequestParam Long id) throws NotFoundException {
        return userMapper.mapToUserDto(userService.getUser(id)
                .orElseThrow(NotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "users")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "users")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        return userMapper.mapToUserDto(userService.addUser(user));
    }

    @RequestMapping(method = RequestMethod.POST, value = "users")
    public void createUser(@RequestBody UserDto userDto) {
        userService.addUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "users/penalty/{userId}")
    public void payPenalties(@PathVariable("userId") Long userId) throws NotFoundException {
        userService.payPenalties(userId);
    }
}
