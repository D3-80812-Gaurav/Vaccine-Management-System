package com.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exceptions.AadharCardNotFoundException;
import com.app.custom_exceptions.CenterNotFoundException;
import com.app.dtos.BookingDTO;
import com.app.dtos.CenterDTO;
import com.app.dtos.CenterSignInRequest;
import com.app.entities.AadharCard;
import com.app.entities.Booking;
import com.app.entities.Center;
import com.app.security.JwtUtils;
import com.app.services.AadharCardService;
import com.app.services.BookingService;
import com.app.services.CenterService;
import com.app.services.VaccinationRecordService;

@RestController
@RequestMapping("/api/center")
@CrossOrigin(origins = "*")
@Validated
public class CenterController {
	@Autowired
	private VaccinationRecordService vaccinationRecordService;
	
	@Autowired
	private AadharCardService aadharCardService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private CenterService centerService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	JwtUtils utils;
	
	@Autowired
	AuthenticationManager mgr;

	@PostMapping("/signin")
	public ResponseEntity<?> centerLogin(@RequestBody CenterSignInRequest request) {
		Authentication verifiedAuth=mgr.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		System.out.println(verifiedAuth.getDetails());
		System.out.println(verifiedAuth.getAuthorities());
		if(verifiedAuth.getAuthorities().iterator().next().getAuthority()!="CENTER") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(utils.generateJwtToken(verifiedAuth));
	}
	@GetMapping("/find/{centerId}")
	public ResponseEntity<CenterDTO> getCenterById(@PathVariable Long centerId){
		Center center=centerService.findById(centerId).orElseThrow(()->new CenterNotFoundException());
		CenterDTO centerDTO=mapper.map(center, CenterDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(centerDTO);
	}

	@GetMapping("/mark_as_vaccinated/{aadharId}")
	public ResponseEntity<String> markAsVaccinated(@PathVariable Long aadharId) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName().toString();
		Center center=centerService.findByEmail(email).orElseThrow(()->new CenterNotFoundException("Center Not Found"));
		AadharCard aadharCard=aadharCardService.findById(aadharId).orElseThrow(()->new AadharCardNotFoundException(aadharId));
		boolean recordAdded = vaccinationRecordService.addRecord(center,aadharCard);
		if (recordAdded) {
//			Booking booking = bookingService.getBookingByAadharId(record.getAadharCardId());
//			bookingService.cancelAppointment(booking);
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Added Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed To Add Record");
		}
	}
	
	@GetMapping("/centers/{pinCode}")
	public List<CenterDTO> getCentersByPinCode(@PathVariable String pinCode) {
		return centerService.findByPinCodeAndStockAvailability(pinCode, 0).stream()
				.map(center -> mapper.map(center, CenterDTO.class)).collect(Collectors.toList());
	}

	@GetMapping("/appointments")
	public ResponseEntity<List<BookingDTO>> getAppBookings() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName().toString();
		Center center=centerService.findByEmail(email).orElseThrow(()->new CenterNotFoundException("Center Not Found"));
		List<BookingDTO> bookings = bookingService.getBookingsByCenterId(center.getId());
		return ResponseEntity.status(HttpStatus.OK).body(bookings);
	}
	@GetMapping("/center_dashboard")
	public ResponseEntity<CenterDTO> getCenterDetailsById(){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName().toString();
		Center center=centerService.findByEmail(email).orElseThrow(()->new CenterNotFoundException("Center Not Found"));
		if(center!=null)
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(center, CenterDTO.class));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	@DeleteMapping("/appointment/cancel/{aadharId}")
	public ResponseEntity<String> cancelAppointment(@PathVariable Long aadharId) {
		System.out.println("In DELETE MAPPING");
		Booking booking = bookingService.getBookingByAadharId(aadharId);
		System.out.println(booking.getId());
		bookingService.cancelAppointment(booking);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Cancelled Appointment Successfully");
	}
}
