package com.zenloww.service;

import com.zenloww.dto.UserDto;
import com.zenloww.entity.User;
import com.zenloww.mapper.UserMapper;
import com.zenloww.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public UserDto addUser(UserDto userDto) {
        User mappedUserDto = UserMapper.mapToUserEntity(userDto);
        User savedUser = userRepository.save(mappedUserDto);
        return UserMapper.mapToUserDto(savedUser);
    }

    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }
}
