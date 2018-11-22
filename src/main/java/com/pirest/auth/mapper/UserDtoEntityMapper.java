package com.pirest.auth.mapper;

import org.springframework.stereotype.Component;

import com.pirest.auth.dto.UserDto;
import com.pirest.auth.entity.UserEntity;

@Component
public class UserDtoEntityMapper {
	public UserDto fromUserEntityToUserDto(UserEntity userEntity) {
		UserDto userDto = new UserDto();
		userDto.setPassword(userEntity.getPassword());
		userDto.setUserName(userEntity.getUserName());
		return userDto;
	}

	public UserEntity fromUserDtoToUserEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(userDto.getUserName());
		userEntity.setPassword(userDto.getPassword());
		return userEntity;
	}
}
