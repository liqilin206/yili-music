package com.ynzt.yilimusic.controller;

import com.ynzt.yilimusic.dto.UserCreateRequest;
import com.ynzt.yilimusic.dto.UserDto;
import com.ynzt.yilimusic.mapper.UserMapper;
import com.ynzt.yilimusic.service.UserService;
import com.ynzt.yilimusic.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    UserMapper userMapper;

    @PostMapping("list")
    List<UserVo> list(@RequestBody UserDto userDto){
        List<UserVo> list = userService.list(userDto).stream().map(userMapper::toVo).collect(Collectors.toList());
        return list;
    }

    @PostMapping("")
    UserVo create(@Validated @RequestBody UserCreateRequest userCreateRequest){
        return userMapper.toVo(userService.create(userCreateRequest)) ;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
