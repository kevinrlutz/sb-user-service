package com.perficient.userservice.services;

import com.perficient.userservice.model.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto getUserById(UUID userId);
    UserDto saveNewUser(UserDto userDto);
    UserDto updateUser(UUID userId, UserDto userDto);
    void deleteUser(UUID userId);
}
