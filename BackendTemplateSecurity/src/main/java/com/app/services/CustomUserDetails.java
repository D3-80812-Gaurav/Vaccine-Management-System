package com.app.services;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.app.entities.Admin;
import com.app.entities.Center;
import com.app.entities.Citizen;
import com.app.entities.UserEntity;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	UserEntity user;
	
	public CustomUserDetails(Citizen citizen) {
		this.user=citizen;
	}
	
	public CustomUserDetails(Center center) {
		this.user=center;
	}
	
	public CustomUserDetails(Admin admin) {
		this.user=admin;
	}
 
	public CustomUserDetails(UserEntity user) {
		super();
		this.user = user;
	}
	
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> role=new ArrayList<>();
		role.add(new SimpleGrantedAuthority(user.getRole().name()));
		return role;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
