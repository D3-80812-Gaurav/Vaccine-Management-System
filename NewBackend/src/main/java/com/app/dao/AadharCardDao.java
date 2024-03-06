package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.AadharCard;

public interface AadharCardDao extends JpaRepository<AadharCard, Long> {
	
}
