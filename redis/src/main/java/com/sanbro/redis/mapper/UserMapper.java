package com.sanbro.redis.mapper;

import com.sanbro.redis.dto.UserDto;
import com.sanbro.redis.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User UserDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
