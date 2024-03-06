package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Admin;
import com.app.entities.Citizen;

public interface AdminDao extends JpaRepository<Admin, Long>{
	Optional<Admin> findByNameAndPassword(String name,String password);

}
