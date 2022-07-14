package com.perficient.userservice.services;

import com.perficient.userservice.web.model.UserDto;
import com.perficient.userservice.repositories.UserRepository;
import com.perficient.userservice.web.mappers.UserMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@AutoConfigureDataMongo
class UserServiceTest {

    // INTEGRATION TEST - PERSISTS DATA IN MONGO DB

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Test
    void getUserById() {
        UserDto userDto = getValidUserDto();
        UserDto savedUser = userService.saveNewUser(userDto);
        String testId = savedUser.getId();

        UserDto returnedUserDto = userService.getUserById(testId);
        System.out.println("Returned UserDto: " + returnedUserDto);

        assertNotNull(returnedUserDto);
    }

    @Test
    void saveNewUser() throws Exception {
        UserDto userDto = getValidUserDto();

        UserDto returnedUserDto = userService.saveNewUser(userDto);
        System.out.println("Returned UserDto: " + returnedUserDto);

        assertNotNull(returnedUserDto);
    }

    @Test
    void updateUser() throws Exception {
        UserDto userDto = getValidUserDto();
        UserDto savedUser = userService.saveNewUser(userDto);

        System.out.println("Saved UserDto: " + savedUser);

        UserDto updateDto = UserDto.builder()
                .firstName("Updated")
                .lastName("User")
                .email("updatedemail@email.com")
                .phoneNumber("0987654321")
                .gender("Female")
                .age(30)
                .build();

        UserDto updatedUser = userService.updateUser(savedUser.getId(), updateDto);
        System.out.println("Updated UserDto: " + updatedUser);

        assertNotNull(updatedUser);
    }

    @Test
    void deleteUser() {
        UserDto savedUser = userService.saveNewUser(getValidUserDto());
        String testId = savedUser.getId();
        userService.deleteUser(testId);

        assertThat(userRepository.findById(testId)).isEmpty();
    }

    UserDto getValidUserDto() {
        return UserDto.builder()
                .id(new ObjectId().toString())
                .firstName("Joey")
                .lastName("Test2")
                .email("test2@test.com")
                .phoneNumber("1234567890")
                .gender("Male")
                .age(17)
                .build();
    }
}