package com.perficient.userservice.web.mappers;

import com.perficient.userservice.domain.User;
import com.perficient.userservice.web.model.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);

    List<UserDto> toUserDtoList(List<User> users);
}
