package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Citizen;

public interface CitizenDao extends JpaRepository<Citizen, Long> {

}
