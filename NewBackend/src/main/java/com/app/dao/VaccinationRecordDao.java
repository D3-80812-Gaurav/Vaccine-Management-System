package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.VaccinationRecord;
import com.app.entities.AadharCard;
import com.app.entities.Gender;

public interface VaccinationRecordDao extends JpaRepository<VaccinationRecord, Long> {

	List<VaccinationRecord> findByAadharCardId(Long aadharId);

	@Query(value = "SELECT COUNT(*) FROM aadhar_cards INNER JOIN vaccination_records ON aadhar_cards.id = vaccination_records.addhar_id WHERE aadhar_cards.gender = 'FEMALE'", nativeQuery = true)
    Long countFemaleVaccinationRecords();
	
	@Query(value = "SELECT COUNT(*) FROM aadhar_cards INNER JOIN vaccination_records ON aadhar_cards.id = vaccination_records.addhar_id WHERE aadhar_cards.gender = 'MALE'", nativeQuery = true)
    Long countMaleVaccinationRecords();
}
