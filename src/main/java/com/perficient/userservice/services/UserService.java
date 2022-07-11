package com.perficient.userservice.services;

import com.perficient.userservice.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService {
    UserDto getUserById(String userId);
    UserDto saveNewUser(UserDto userDto);
    UserDto updateUser(String userId, UserDto userDto);
    void deleteUser(String userId);
}
