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

import com.pirest.auth.dto.UserDto;
import com.pirest.auth.entity.UserEntity;
import com.pirest.auth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(userId);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());
	}

	private List<GrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<UserDto> findAll() {
		List<UserDto> list = new ArrayList<UserDto>();
		// userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	public void delete(long id) {
		userRepository.delete(id);
	}

	public UserDto save(UserEntity user) {
		userRepository.save(user);
		return null;
	}

}
