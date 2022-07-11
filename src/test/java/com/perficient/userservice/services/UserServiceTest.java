package com.perficient.userservice.services;

import com.perficient.userservice.domain.User;
import com.perficient.userservice.model.UserDto;
import com.perficient.userservice.repositories.UserRepository;
import com.perficient.userservice.web.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void getUserById() {

    }

    @Test
    void saveNewUser() throws Exception {
        UserDto userDto = getValidUserDto();
        System.out.println(userDto);

        userService.saveNewUser(userDto);

        verify(userRepository, times(1)).save(any(User.class));
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
                .userId(UUID.randomUUID().toString())
                .build();
    }
}