package com.app.services;

import java.util.Optional;

import com.app.entities.Admin;

public interface AdminService {
	Optional<Admin> findByNameAndPassword(String name,String password);
}
