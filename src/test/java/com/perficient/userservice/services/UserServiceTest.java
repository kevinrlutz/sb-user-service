package com.perficient.userservice.services;

import com.perficient.userservice.web.model.ApptDto;
import com.perficient.userservice.web.model.ApptTypeEnum;
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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

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
                .phoneNumber("098-765-4321")
                .gender("Female")
                .age(30)
                .appointmentList(savedUser.getAppointmentList())
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

    @Test
    void addAppointment() {
        UserDto savedUser = userService.saveNewUser(getValidUserDto());
        String testId = savedUser.getId();
        userService.addAppointment(testId, getValidApptDto());

        System.out.println(userService.getUserAppointments(testId));

        assertThat(userService.getUserAppointments(testId).isEmpty()).isFalse();
    }

    @Test
    void addFiveUsersWithDummyAppts() {
        for (int i = 0; i < 5; i++) {
            UserDto savedUser = userService.saveNewUser(getValidUserDto());
            String testId = savedUser.getId();
            userService.addAppointment(testId, getValidApptDto());
        }

        assertTrue(userRepository.count() >= 5);
    }

    UserDto getValidUserDto() {
        return UserDto.builder()
                .id(new ObjectId().toString())
                .firstName("New")
                .lastName("User")
                .email("test@dummytest.com")
                .phoneNumber("555-555-5555")
                .gender("Female")
                .age(75)
                .appointmentList(new ArrayList<>())
                .build();
    }

    ApptDto getValidApptDto() {
        return ApptDto.builder()
                .id(new ObjectId().toString())
                .apptName("Test Appt")
                .apptType(ApptTypeEnum.INITIAL)
                .description("Test Appt Description")
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .build();
    }
}