package com.pirest.auth.dto;

import org.springframework.security.core.userdetails.User;

import com.pirest.auth.entity.UserEntity;

public class CustomizedUser extends User {

	private static final long serialVersionUID = 1L;

	public CustomizedUser(UserEntity userEntity) {
		super(userEntity.getPassword(), userEntity.getPassword(), userEntity.getGrantedAuthoritiesLitst());
	}
}
