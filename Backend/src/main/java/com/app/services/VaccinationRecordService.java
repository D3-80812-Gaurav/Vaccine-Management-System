package com.app.services;

import java.util.List;
import java.util.Optional;

import com.app.dtos.AddVaccinationRecord;
import com.app.entities.VaccinationRecord;

public interface VaccinationRecordService {
	boolean addRecord(AddVaccinationRecord record);
	List<VaccinationRecord> getVaccinationRecords(Long aadharId);
}
