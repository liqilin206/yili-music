package com.ynzt.yilimusic.service;

import com.ynzt.yilimusic.dto.UserCreateRequest;
import com.ynzt.yilimusic.dto.UserDto;
import com.ynzt.yilimusic.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto create(UserCreateRequest userCreateRequest);

    List<UserDto> list(UserDto userDto);

    @Override
    User loadUserByUsername(String username);
}
