package com.securityEmpl.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User{

	private static final long serialVersionUID = 1L;
    private Integer user_id;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer user_id) {
        super(username, password, authorities);
        this.user_id = user_id;
    }

	public Integer getUser_id() {
		return user_id;
	}
}
