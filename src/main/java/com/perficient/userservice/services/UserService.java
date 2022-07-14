package com.perficient.userservice.services;

import com.perficient.userservice.web.model.UserDto;

public interface UserService {
    UserDto getUserById(String userId);
    UserDto saveNewUser(UserDto userDto);
    UserDto updateUser(String userId, UserDto userDto);
    void deleteUser(String userId);
}
