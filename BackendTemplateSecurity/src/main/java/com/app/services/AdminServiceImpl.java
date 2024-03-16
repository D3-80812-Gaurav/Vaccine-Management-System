package com.app.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AdminDao;
import com.app.entities.Admin;

@Transactional
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminDao adminDao;

	@Override
	public Optional<Admin> findByNameAndPassword(String name, String password) {
		// TODO Auto-generated method stub
		return adminDao.findByNameAndPassword(name, password);
	}
	
	
}
