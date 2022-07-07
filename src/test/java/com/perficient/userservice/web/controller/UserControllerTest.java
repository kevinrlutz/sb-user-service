package com.perficient.userservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.userservice.model.UserDto;
import com.perficient.userservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    void getUser() throws Exception {

        given(userService.getUserById(any())).willReturn(getValidUserDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createUser() throws Exception {
        UserDto userDto = getValidUserDto();

        String userDtoJson = objectMapper.writeValueAsString(userDto);

        given(userService.saveNewUser(any())).willReturn(getValidUserDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateUser() throws Exception {
        given(userService.updateUser(any(), any())).willReturn(getValidUserDto());

        UserDto userDto = UserDto.builder().build();
        String userDtoJson = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson))
                .andExpect(status().isNoContent());
    }

    UserDto getValidUserDto() {
        return UserDto.builder()
                .userId(UUID.randomUUID())
                .firstName("Joe")
                .lastName("Bloggs")
                .gender("Male")
                .age(18)
                .email("test@test.com")
                .phoneNumber("555-555-5555")
                .build();
    }
}