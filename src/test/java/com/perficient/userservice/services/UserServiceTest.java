package com.perficient.userservice.services;

import com.perficient.userservice.model.UserDto;
import com.perficient.userservice.web.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    @MockBean
    UserService userService;

    @MockBean
    UserMapper userMapper;

    @Test
    void getUserById() {

    }

    @Test
    void saveNewUser() {
        // TODO: this is failing, not sure why yet
        UserDto userDto = getValidUserDto();
        assertNotNull(userService.saveNewUser(userDto));
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    UserDto getValidUserDto() {
        return UserDto.builder()
                .firstName("Joe")
                .lastName("Test")
                .email("test@test.com")
                .phoneNumber("1234567890")
                .gender("Male")
                .age(17)
                .userId(UUID.randomUUID())
                .build();
    }
}