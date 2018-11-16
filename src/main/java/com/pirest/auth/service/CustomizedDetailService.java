package com.pirest.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pirest.auth.dao.OAuthDAO;
import com.pirest.auth.dto.CustomizedUser;
import com.pirest.auth.entity.UserEntity;

@Service
public class CustomizedDetailService implements UserDetailsService {

	@Autowired
	private OAuthDAO oauthDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = oauthDAO.getUserDetails(username);
		CustomizedUser customizedUser = new CustomizedUser(userEntity);
		return customizedUser;
	}

}
