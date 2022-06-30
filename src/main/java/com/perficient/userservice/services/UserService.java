package com.perficient.userservice.services;

import com.perficient.model.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto getUserById(UUID userId);
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(UUID userId, UserDto userDto);
    UserDto deleteUser(UUID userId);
}
