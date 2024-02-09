package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.VaccinationRecord;

public interface VaccinationRecordDao extends JpaRepository<VaccinationRecord, Long>{

}
