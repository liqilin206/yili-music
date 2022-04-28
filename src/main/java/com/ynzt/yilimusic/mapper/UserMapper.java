package com.ynzt.yilimusic.mapper;

import com.ynzt.yilimusic.dto.UserCreateRequest;
import com.ynzt.yilimusic.dto.UserDto;
import com.ynzt.yilimusic.entity.User;
import com.ynzt.yilimusic.vo.UserVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    List<UserDto> toDto(List<User> list);

    UserVo toVo(UserDto userDto);

    User createEntity(UserCreateRequest userCreateRequest);

   // UserDto toDto(UserVo userVo);
}
