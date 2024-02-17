package com.app.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.AadharCardNotFoundException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AadharCardDao;
import com.app.dao.BookingDao;
import com.app.dao.CitizenDao;
import com.app.dao.VaccinationRecordDao;
import com.app.dtos.CitizenDTO;
import com.app.dtos.CitizenSignInRequest;
import com.app.entities.AadharCard;
import com.app.entities.Booking;
import com.app.entities.Citizen;
import com.app.entities.Review;
import com.app.entities.VaccinationRecord;

@Service
@Transactional
public class CitizenServiceImpl implements CitizenService {
	@Autowired
	CitizenDao citizenDao;
	@Autowired
	ModelMapper mapper;

	@Autowired
	AadharCardDao aadharDao;

	@Autowired
	VaccinationRecordDao vaccinationRecordDao;

	@Autowired
	BookingDao bookingDao;

	@Override
	public CitizenDTO authenticateCitizen(CitizenSignInRequest request) {
		Optional<Citizen> citizen = citizenDao.findByAadharAndPassword(request.getAadharID(), request.getPassword());
		citizen.orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials"));

		Citizen c = citizen.get();
		Long aadharId = c.getAadharCard().getId();
		AadharCard a = aadharDao.findById(aadharId)
				.orElseThrow(() -> new AadharCardNotFoundException());

		List<VaccinationRecord> vaccinationRecords = vaccinationRecordDao.findByAadharCardId(aadharId);
		Optional<Booking> bookingRecord = bookingDao.findByAadharCard(a);

		CitizenDTO citizenView = new CitizenDTO();
		citizenView.setCitizenID(c.getId());
		citizenView.setAadharID(a.getId());
		citizenView.setFirstName(a.getFirstName());
		citizenView.setLastName(a.getLastName());
		citizenView.setPhoneNo(c.getPhoneNo());
		citizenView.setState(a.getState());
		citizenView.setDistrict(a.getState());
		citizenView.setCity(a.getCity());
		citizenView.setPinCode(a.getPinCode());
		citizenView.setDob(a.getDob());
		citizenView.setGender(a.getGender());

		Review review1 = null;
		Review review2 = null;

		if (vaccinationRecords.size() == 0) {
			// Haven't Taken Any Dose
			citizenView.setHasTakenFirstDose(false);
			citizenView.setHasTakenSecondDose(false);
			citizenView.setHasBookedSecondDose(false);
			citizenView.setHasReviewedFirstDose(false);
			citizenView.setHasReviewedSecondDose(false);
			if (bookingRecord.isPresent()) {
				// Booking of First Dose is Done
				citizenView.setHasBookedFirstDose(true);
			} else {
				// Haven't Booked First Dose Yet
				citizenView.setHasBookedFirstDose(false);
			}
		} else if (vaccinationRecords.size() == 1) {
			// Has Taken A Dose Earlier
			citizenView.setHasTakenFirstDose(true);
			citizenView.setHasTakenSecondDose(false);
			citizenView.setHasBookedSecondDose(false);
			if (bookingRecord.isPresent()) {
				citizenView.setHasBookedSecondDose(true);
			} else {
				citizenView.setHasBookedSecondDose(false);
			}
			review1 = vaccinationRecords.get(0).getReview();
			if (review1 == null) {
				// Review
				citizenView.setHasReviewedFirstDose(false);
			} else {
				citizenView.setHasReviewedFirstDose(true);
			}
			citizenView.setHasReviewedSecondDose(false);
		} else if (vaccinationRecords.size() == 2) {
			// Has Taken Both The Doses
			citizenView.setHasTakenFirstDose(true);
			citizenView.setHasTakenSecondDose(true);
			citizenView.setHasBookedFirstDose(false);
			citizenView.setHasBookedSecondDose(false);
			review1 = vaccinationRecords.get(0).getReview();
			review2 = vaccinationRecords.get(1).getReview();
			if (review1 == null) {
				// Review
				citizenView.setHasReviewedFirstDose(false);
			} else {
				citizenView.setHasReviewedFirstDose(true);
			}
			if (review2 == null) {
				// Review
				citizenView.setHasReviewedSecondDose(false);
			} else {
				citizenView.setHasReviewedSecondDose(true);
			}
		}
		return citizenView;
	}

	public CitizenDTO getCitizenByAadharId(Long citizenId) {
		Optional<Citizen> citizen=citizenDao.findByAadharCardId(citizenId);
		citizen.orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials"));

		Citizen c = citizen.get();
		Long aadharId = c.getAadharCard().getId();
		AadharCard a = aadharDao.findById(aadharId)
				.orElseThrow(() -> new AadharCardNotFoundException());

		List<VaccinationRecord> vaccinationRecords = vaccinationRecordDao.findByAadharCardId(aadharId);
		Optional<Booking> bookingRecord = bookingDao.findByAadharCard(a);

		CitizenDTO citizenView = new CitizenDTO();
		citizenView.setCitizenID(c.getId());
		citizenView.setAadharID(a.getId());
		citizenView.setFirstName(a.getFirstName());
		citizenView.setLastName(a.getLastName());
		citizenView.setPhoneNo(c.getPhoneNo());
		citizenView.setState(a.getState());
		citizenView.setDistrict(a.getState());
		citizenView.setCity(a.getCity());
		citizenView.setPinCode(a.getPinCode());
		citizenView.setDob(a.getDob());
		citizenView.setGender(a.getGender());

		Review review1 = null;
		Review review2 = null;

		if (vaccinationRecords.size() == 0) {
			// Haven't Taken Any Dose
			citizenView.setHasTakenFirstDose(false);
			citizenView.setHasTakenSecondDose(false);
			citizenView.setHasBookedSecondDose(false);
			citizenView.setHasReviewedFirstDose(false);
			citizenView.setHasReviewedSecondDose(false);
			if (bookingRecord.isPresent()) {
				// Booking of First Dose is Done
				citizenView.setHasBookedFirstDose(true);
			} else {
				// Haven't Booked First Dose Yet
				citizenView.setHasBookedFirstDose(false);
			}
		} else if (vaccinationRecords.size() == 1) {
			// Has Taken A Dose Earlier
			citizenView.setHasTakenFirstDose(true);
			citizenView.setHasTakenSecondDose(false);
			citizenView.setHasBookedSecondDose(false);
			if (bookingRecord.isPresent()) {
				citizenView.setHasBookedSecondDose(true);
			} else {
				citizenView.setHasBookedSecondDose(false);
			}
			review1 = vaccinationRecords.get(0).getReview();
			if (review1 == null) {
				// Review
				citizenView.setHasReviewedFirstDose(false);
			} else {
				citizenView.setHasReviewedFirstDose(true);
			}
			citizenView.setHasReviewedSecondDose(false);
		} else if (vaccinationRecords.size() == 2) {
			// Has Taken Both The Doses
			citizenView.setHasTakenFirstDose(true);
			citizenView.setHasTakenSecondDose(true);
			citizenView.setHasBookedFirstDose(false);
			citizenView.setHasBookedSecondDose(false);
			review1 = vaccinationRecords.get(0).getReview();
			review2 = vaccinationRecords.get(1).getReview();
			if (review1 == null) {
				// Review
				citizenView.setHasReviewedFirstDose(false);
			} else {
				citizenView.setHasReviewedFirstDose(true);
			}
			if (review2 == null) {
				// Review
				citizenView.setHasReviewedSecondDose(false);
			} else {
				citizenView.setHasReviewedSecondDose(true);
			}
		}
		return citizenView;
	}

}
