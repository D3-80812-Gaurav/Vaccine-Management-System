package com.app.services;

import java.util.List;
import com.app.entities.AadharCard;
import com.app.entities.Center;
import com.app.entities.VaccinationRecord;

public interface VaccinationRecordService {
	boolean addRecord(Center center,AadharCard aadharCard);
	List<VaccinationRecord> getVaccinationRecords(Long aadharId);
	Long getNumberOfRecords();
	Long getNumberOfFemaleRecords();
	Long getNumberOfMaleRecords();
}
