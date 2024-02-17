package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dtos.AddVaccinationRecord;
import com.app.dtos.BookingDTO;
import com.app.dtos.CenterDTO;
import com.app.dtos.CenterSignInRequest;
import com.app.entities.Booking;
import com.app.services.BookingService;
import com.app.services.CenterService;
import com.app.services.VaccinationRecordService;

@RestController
@RequestMapping("/center")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CenterController {
	@Autowired
	private VaccinationRecordService vaccinationRecordService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private CenterService centerService;

	@PostMapping("/login")
	public ResponseEntity<CenterDTO> getCenterByCenterIdAndPassword(@RequestBody CenterSignInRequest request) {
		CenterDTO centerDTO = centerService.findByIdAndPassword(request.getCenterId(), request.getPassword());
		return ResponseEntity.status(HttpStatus.OK).body(centerDTO);
	}

	@PostMapping("/markAsVaccinated")
	public ResponseEntity<String> markAsVaccinated(@RequestBody AddVaccinationRecord record) {
		boolean recordAdded = vaccinationRecordService.addRecord(record);
		if (recordAdded) {
			Booking booking = bookingService.getBookingByAadharId(record.getAadharCardId());
			bookingService.cancelAppointment(booking);
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Added Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed To Add Record");
		}
	}

	@PostMapping("/appointments")
	public ResponseEntity<List<BookingDTO>> getAppBookings(@RequestBody Long centerId) {
		List<BookingDTO> bookings = bookingService.getBookingsByCenterId(centerId);
		return ResponseEntity.status(HttpStatus.OK).body(bookings);
	}
}
