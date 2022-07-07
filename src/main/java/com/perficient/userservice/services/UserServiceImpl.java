package com.perficient.userservice.services;

import com.perficient.userservice.domain.User;
import com.perficient.userservice.web.controller.NotFoundException;
import com.perficient.userservice.web.mappers.UserMapper;
import com.perficient.userservice.model.UserDto;
import com.perficient.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserById(UUID userId) {
        return userMapper.toUserDto(userRepository.findById(userId).orElseThrow(NotFoundException::new));
    }

    @Override
    public UserDto saveNewUser(UserDto userDto) {
        return userMapper.toUserDto(userRepository.save(userMapper.toUser(userDto)));
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());

        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());

        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.delete(userRepository.findById(userId).orElseThrow(NotFoundException::new));
        System.out.println("Deleted user with id: " + userId);
    }


}
