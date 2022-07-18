package com.perficient.userservice.services;

import com.perficient.userservice.web.model.ApptDto;
import com.perficient.userservice.web.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(String userId);
    UserDto saveNewUser(UserDto userDto);
    UserDto updateUser(String userId, UserDto userDto);
    void deleteUser(String userId);

    List<UserDto> getAllUsers();

    List<ApptDto> getUserAppointments(String userId);

    ApptDto addAppointment(String userId, ApptDto apptDto);
}
