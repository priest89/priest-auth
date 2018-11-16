package com.pirest.auth.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserEntity {
	private String userName;
	private String password;
	private Collection<GrantedAuthority> grantedAuthoritiesLitst = new ArrayList<GrantedAuthority>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<GrantedAuthority> getGrantedAuthoritiesLitst() {
		return grantedAuthoritiesLitst;
	}

	public void setGrantedAuthoritiesLitst(Collection<GrantedAuthority> grantedAuthoritiesLitst) {
		this.grantedAuthoritiesLitst = grantedAuthoritiesLitst;
	}

}
