package com.ynzt.yilimusic.service.impl;

import com.ynzt.yilimusic.dto.UserCreateRequest;
import com.ynzt.yilimusic.dto.UserDto;
import com.ynzt.yilimusic.entity.User;
import com.ynzt.yilimusic.exception.BizException;
import com.ynzt.yilimusic.exception.ExceptionType;
import com.ynzt.yilimusic.mapper.UserMapper;
import com.ynzt.yilimusic.repository.UserRepository;
import com.ynzt.yilimusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserMapper userMapper;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        checkUsername(userCreateRequest.getUsername());
        User user = userMapper.createEntity(userCreateRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  userMapper.toDto(userRepository.save(user)) ;
    }

    @Override
    public List<UserDto> list(UserDto userDto) {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
        // return userMapper.toDto(userRepository.getByUsername(userDto.getUsername()));
    }

    @Override
    public User loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    private void checkUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
