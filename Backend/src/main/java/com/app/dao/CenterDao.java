package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Center;

public interface CenterDao extends JpaRepository<Center, Long> {
	
	List<Center> findByPincode(String pincode);
	
	List<Center> findByPincodeAndStockGreaterThan(String pincode,int stock);
}
