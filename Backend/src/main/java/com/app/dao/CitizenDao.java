package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Citizen;

public interface CitizenDao extends JpaRepository<Citizen, Long> {
	public Citizen save(Citizen citizen);
	
	//Optional<Citizen> findByAadharCardAndPassword(Long aadhar_id,String password);
}
