package com.pirest.auth.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDtoEntityMapper userMapper;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority());
	}

	private List<GrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<UserDto> findAll() {
		List<UserDto> userDtos = new ArrayList<UserDto>();
//		userRepository.findAll().iterator().forEachRemaining(list::add);
		List<UserEntity> userEntities = userRepository.findAll();
		if (!CollectionUtils.isEmpty(userEntities)) {
			userEntities.forEach(userEntity -> userDtos.add(userMapper.fromUserEntityToUserDto(userEntity)));
		}
		return userDtos;
	}

	public void delete(long id) {
		userRepository.delete(id);
	}

	public UserDto save(UserDto userDto) {
		UserEntity userEntity = userMapper.fromUserDtoToUserEntity(userDto);
		userEntity = userRepository.save(userEntity);
		return userMapper.fromUserEntityToUserDto(userEntity);
	}
	
	public UserDto get(Long id) {
		UserEntity userEntity = userRepository.findOne(id);
		return userMapper.fromUserEntityToUserDto(userEntity);
	}

}
