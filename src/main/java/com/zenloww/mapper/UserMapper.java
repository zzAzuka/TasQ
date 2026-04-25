package com.zenloww.mapper;

import com.zenloww.dto.UserDto;
import com.zenloww.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    public static User mapToUserEntity(UserDto userdto) {
        return new User(
                userdto.getUsername(),
                userdto.getEmail(),
                userdto.getRole()
        );
    }
}
