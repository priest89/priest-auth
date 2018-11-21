package com.pirest.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.pirest.auth.dto.UserDto;
import com.pirest.auth.entity.UserEntity;

@Mapper
public interface UserDtoEntityMapper {
	@Mappings({ @Mapping(target = "userName", source = "userEntity.userName"),
			@Mapping(target = "password", source = "userEntity.password") })
	UserDto fromUserEntityToUserDto(UserEntity userEntity);

	@Mappings({ @Mapping(target = "userName", source = "userDto.userName"),
			@Mapping(target = "password", source = "userDto.password") })
	UserEntity fromUserDtoToUserEntity(UserDto userDto);
}
