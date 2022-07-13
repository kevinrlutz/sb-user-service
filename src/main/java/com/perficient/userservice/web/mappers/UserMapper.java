package com.perficient.userservice.web.mappers;

import com.perficient.userservice.domain.User;
import com.perficient.userservice.model.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
