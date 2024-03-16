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
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	CitizenDao citizenDao;

	@Autowired
	CenterDao centerDao;

	@Autowired
	AdminDao adminDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Citizen citizen = citizenDao.findByEmail(username).orElse(null);
		if (citizen != null) {
			UserEntity user = new UserEntity(citizen.getEmail(), citizen.getPassword(), Role.CITIZEN);
			return new CustomUserDetails(user);
		} else {
			Center center = centerDao.findByEmail(username).orElse(null);
			if (center != null) {
				UserEntity user = new UserEntity(center.getEmail(), center.getPassword(), Role.CENTER);
				return new CustomUserDetails(user);
			} else {
				Admin admin = adminDao.findByEmail(username).orElse(null);
				if (admin != null) {
					UserEntity user = new UserEntity(admin.getEmail(), admin.getPassword(), Role.ADMIN);
					return new CustomUserDetails(user);
				} else {
					throw new UsernameNotFoundException("User associated with " + username + " not found.");
				}
			}
		}
	}

}
