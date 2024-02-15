package com.app.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AadharCardDao;
import com.app.dao.BookingDao;
import com.app.dao.CenterDao;
import com.app.dao.VaccinationRecordDao;
import com.app.dtos.AddVaccinationRecord;
import com.app.entities.AadharCard;
import com.app.entities.Booking;
import com.app.entities.Center;
import com.app.entities.Review;
import com.app.entities.VaccinationRecord;

@Service
public class VaccinationRecordServiceImpl implements VaccinationRecordService {
	@Autowired
	VaccinationRecordDao vaccinationRecordDao;

	@Autowired
	AadharCardDao aadharCardDao;

	@Autowired
	CenterDao centerDao;
	
	@Autowired
	BookingDao bookingDao;

	@Override
	@Transactional
	public boolean addRecord(AddVaccinationRecord record) {
		Center center=centerDao.findById(record.getCenterId()).orElseThrow(()->new ResourceNotFoundException("Center Associated With Given ID not Found"));
		Review review=null;
		AadharCard aadharCard=aadharCardDao.findById(record.getAadharCardId()).orElseThrow(()->new ResourceNotFoundException("Aadhar Associated With Given ID not Found"));
		VaccinationRecord r=vaccinationRecordDao.save(new VaccinationRecord(center,review,aadharCard,LocalDate.now()));
		Booking booking=bookingDao.findByAadharCard(aadharCard).orElseThrow(()->new ResourceNotFoundException("You Have Not Earlier Booked The Slot"));
		bookingDao.delete(booking);
		if(r!=null)
			return true;
		else
			return false;	
	}
	
	@Override
	public List<VaccinationRecord> getVaccinationRecords(Long aadharId){
		AadharCard card=aadharCardDao.findById(aadharId).orElseThrow(()->new ResourceNotFoundException("Aadhar Associated With Given ID not Found"));
		List<VaccinationRecord> record=vaccinationRecordDao.findByAadharCardId(aadharId);
		return record;
	}
}
