package com.pirest.auth.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.pirest.auth.dto.UserDto;
import com.pirest.auth.entity.UserEntity;
import com.pirest.auth.mapper.UserDtoEntityMapper;
import com.pirest.auth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDtoEntityMapper userMapper;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		LOGGER.info("===== Start service load by user name =====");
		UserEntity user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		LOGGER.info("===== End service load by user name ======");
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority());
	}

	private List<GrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<UserDto> findAll() {
		LOGGER.info("====== Start find all users ======");
		List<UserDto> userDtos = new ArrayList<UserDto>();
		// userRepository.findAll().iterator().forEachRemaining(list::add);
		List<UserEntity> userEntities = userRepository.findAll();
		if (!CollectionUtils.isEmpty(userEntities)) {
			userEntities.forEach(userEntity -> userDtos.add(userMapper.fromUserEntityToUserDto(userEntity)));
		}
		LOGGER.info("===== End find all users =====");
		return userDtos;
	}

	public void delete(long id) {
		LOGGER.info("===== Start delete user by id =====");
		userRepository.delete(id);
		LOGGER.info("===== End delete user by id =====");
	}

	public UserDto save(UserDto userDto) {
		LOGGER.info("===== Start method save user =====");
		UserEntity userEntity = userMapper.fromUserDtoToUserEntity(userDto);
		userEntity = userRepository.save(userEntity);
		LOGGER.info("===== End method message =====");
		return userMapper.fromUserEntityToUserDto(userEntity);
	}

	public UserDto get(Long id) {
		LOGGER.info("===== Start service get user ======");
		UserEntity userEntity = userRepository.findOne(id);
		LOGGER.info("===== End service get user =====");
		return userMapper.fromUserEntityToUserDto(userEntity);
	}

}
