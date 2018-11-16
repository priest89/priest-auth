package com.pirest.auth.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.pirest.auth.entity.UserEntity;

@Repository
public class OAuthDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserEntity getUserDetails(String username) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		String userQuery = "SELECT * FROM USERS WHERE USERNAME = ?";

		List<UserEntity> list = jdbcTemplate.query(userQuery, new String[] { username }, (ResultSet rs, int rowNum) -> {
			UserEntity user = new UserEntity();
			user.setUserName(username);
			user.setPassword(rs.getString("PASSWORD"));
			return user;
		});

		if (list.size() > 0) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEM_ADMIN");
			grantedAuthorities.add(grantedAuthority);
			list.get(0).setGrantedAuthoritiesLitst(grantedAuthorities);
			return list.get(0);
		}
		return null;
	}
}