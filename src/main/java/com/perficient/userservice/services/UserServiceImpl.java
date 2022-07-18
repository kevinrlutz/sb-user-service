package com.perficient.userservice.services;

import com.perficient.userservice.domain.User;
import com.perficient.userservice.web.controller.NotFoundException;
import com.perficient.userservice.web.mappers.UserMapper;
import com.perficient.userservice.web.model.ApptDto;
import com.perficient.userservice.web.model.UserDto;
import com.perficient.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public UserDto getUserById(String userId) {
        return userMapper.toUserDto(userRepository.findById(userId).orElseThrow(NotFoundException::new));
    }

    @Override
    public UserDto saveNewUser(UserDto userDto) {
        return userMapper.toUserDto(userRepository.save(userMapper.toUser(userDto)));
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());

        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());

        user.setAppointmentList(userDto.getAppointmentList());

        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.delete(userRepository.findById(userId).orElseThrow(NotFoundException::new));
        System.out.println("Deleted user with id: " + userId);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> users = userMapper.toUserDtoList(userRepository.findAll());
        return users;
    }

    @Override
    public List<ApptDto> getUserAppointments(String userId) {
        UserDto user = userMapper.toUserDto(userRepository.findById(userId).orElseThrow(NotFoundException::new));
        return user.getAppointmentList();
    }

    @Override
    public ApptDto addAppointment(String userId, ApptDto apptDto) {
        UserDto user = userMapper.toUserDto(userRepository.findById(userId).orElseThrow(NotFoundException::new));
        user.getAppointmentList().add(apptDto);
        updateUser(userId, user);
        return apptDto;
    }


}
