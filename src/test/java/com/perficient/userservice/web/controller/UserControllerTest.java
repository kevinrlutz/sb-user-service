package com.perficient.userservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.userservice.web.model.UserDto;
import com.perficient.userservice.services.UserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;


    @Test
    void getAllUsers() throws Exception {
        given(userService.getAllUsers()).willReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {

        given(userService.getUserById(any())).willReturn(getValidUserDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/" + new ObjectId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsersByLastName() throws Exception {
        given(userService.getAllUsersByLastName(any())).willReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON))
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

        UserDto userDto = getValidUserDto();
        userDto.setAge(30);
        String userDtoJson = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/" + new ObjectId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void getUserAppointments() throws Exception {
        given(userService.getUserAppointments(any())).willReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/" + new ObjectId() + "/appointments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/" + new ObjectId()))
                .andExpect(status().isNoContent());
    }

    UserDto getValidUserDto() {
        return UserDto.builder()
                .id(new ObjectId().toString())
                .firstName("Joe")
                .lastName("Bloggs")
                .gender("Male")
                .age(18)
                .email("test@test.com")
                .phoneNumber("555-555-5555")
                .appointmentList(new ArrayList<>())
                .build();
    }
}