package com.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.AdminDao;
import com.app.dao.CenterDao;
import com.app.dao.CitizenDao;
import com.app.entities.Admin;
import com.app.entities.Center;
import com.app.entities.Citizen;
import com.app.entities.Role;
import com.app.entities.UserEntity;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	CitizenDao cDao;
	
	@Autowired
	CenterDao centerDao;
	
	@Autowired
	AdminDao adminDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Citizen citizen=cDao.findByAadharCardId(Long.parseLong(username)).orElse(null);
		Center center=centerDao.findById(Long.parseLong(username)).orElse(null);
		Admin admin=adminDao.findById(Long.parseLong(username)).orElse(null);
		if(citizen!=null) {
			UserEntity user=new UserEntity(citizen.getAadharCard().getId(),citizen.getPassword(),Role.CITIZEN);
			return new CustomUserDetails(user);
		}
		else if(center!=null){
			UserEntity user=new UserEntity(center.getId(),center.getPassword(),Role.CENTER);
			return new CustomUserDetails(user);
		}
		else if(admin!=null) {
			UserEntity user=new UserEntity(admin.getId(),admin.getPassword(),Role.ADMIN);
			return new CustomUserDetails(user);
		}
		else {
			throw new UsernameNotFoundException("User Associated With :"+username+" Not Found");
		}
	}

}
