package com.app.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Center;

public interface CenterDao extends JpaRepository<Center, Long> {
	
	List<Center> findByPincode(String pincode);
	
	List<Center> findByPincodeAndStockGreaterThan(String pincode,int stock);

	Optional<Center> findByIdAndPassword(Long centerId, String password);
}
