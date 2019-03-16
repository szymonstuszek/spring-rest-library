package com.kodilla.library.mapper;

import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getAccountCreationDate(),
                userDto.isActive()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAccountCreationDate(),
                user.isActive()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(u -> new UserDto(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getAccountCreationDate(),
                        u.isActive()
                ))
                .collect(Collectors.toList());
    }
}
