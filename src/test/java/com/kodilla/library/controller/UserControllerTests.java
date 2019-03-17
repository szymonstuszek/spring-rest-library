package com.kodilla.library.controller;

import com.google.gson.*;
import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.UserDto;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void shouldGetEmptyUsersList() throws Exception {
        //Given
        List<User> users = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(users);

        //When & Then
        mockMvc.perform(get("/v1/library/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetUsersList() throws Exception {
        //Given
        List<User> users = new ArrayList<>();
        User user = new User(50L,
                "Johnny",
                "Cash",
                LocalDate.now(),
                true);
        users.add(user);

        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto(50L,
                "Johnny",
                "Cash",
                LocalDate.now(),
                true);
        userDtoList.add(userDto);

        when(userService.getAllUsers()).thenReturn(users);
        when(userMapper.mapToUserDtoList(users)).thenReturn(userDtoList);

        //When & Then
        mockMvc.perform(get("/v1/library/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(50)));
    }


    //how to make this work?
    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        Gson gson = new GsonBuilder().setDateFormat("dd-mm-yyyy").create();

        UserDto userDto = new UserDto(
                50L,
                "Michael",
                "Jordan",
                LocalDate.now(),
                true
        );

        User user = new User(
                50L,
                "Michael",
                "Jordan",
                LocalDate.now(),
                true
        );

        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userService.addUser(Matchers.any(User.class))).thenReturn(user);

        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(post("/v1/library/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

}
