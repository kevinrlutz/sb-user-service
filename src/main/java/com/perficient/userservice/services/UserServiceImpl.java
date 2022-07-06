package com.perficient.userservice.services;

import com.perficient.userservice.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserDto getUserById(UUID userId) {

        // TODO: Create a method in the UserRepository to get a user by id
        return UserDto.builder().userId(UUID.randomUUID())
                .age(22)
                .email("kevinlutz429@gmail.com")
                .firstName("Joe")
                .lastName("Test")
                .gender("Male")
                .phoneNumber("555-555-5555")
                .build();
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        // TODO: Create a method to save new user to UserRepository
        return UserDto.builder().userId(UUID.randomUUID()).build();
    }

    @Override
    public void updateUser(UUID userId, UserDto userDto) {
        // TODO: Create a method to update a user in UserRepository
        log.debug("Updating user with id: {}", userId);
    }

    @Override
    public void deleteUser(UUID userId) {
        // TODO: Create a method to delete a user in UserRepository
        log.debug("Deleting user with id: {}", userId);
    }


}
