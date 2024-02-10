package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Center;

public interface CenterDao extends JpaRepository<Center, Long> {
	public Center save(Center center);
	
	List<Center> findByPinCode(String pincode);
}
