package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.VaccinationRecord;
import com.app.entities.AadharCard;

public interface VaccinationRecordDao extends JpaRepository<VaccinationRecord, Long> {

	List<VaccinationRecord> findByAadharCardId(Long aadharId);
}
