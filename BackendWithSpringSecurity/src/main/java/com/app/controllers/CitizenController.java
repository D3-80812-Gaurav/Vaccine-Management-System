package com.app.controllers;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exceptions.AadharCardNotFoundException;
import com.app.custom_exceptions.CenterNotFoundException;
import com.app.custom_exceptions.RegistrationAlreadyDoneException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.custom_exceptions.StockNotAvailableException;
import com.app.dtos.AppointmentDetailsDTO;
import com.app.dtos.BookVaccineDTO;
import com.app.dtos.CitizenDTO;
import com.app.dtos.CitizenSignInRequest;
import com.app.dtos.RegisterCitizenForm;
import com.app.dtos.StatisticsDTO;
import com.app.services.AadharCardService;
import com.app.services.BookingService;
import com.app.services.CenterService;
import com.app.services.CitizenService;
import com.app.services.QRCodeGeneratorService;
import com.app.services.VaccinationRecordService;
import com.google.zxing.WriterException;

import com.app.dtos.VaccinationCertificate;
import com.app.entities.AadharCard;
import com.app.entities.Booking;
import com.app.entities.Center;
import com.app.entities.Citizen;
import com.app.entities.VaccinationRecord;
import com.app.security.JwtUtils;
//Security Implementation Done
@RestController
@RequestMapping("/api/citizen")
@CrossOrigin(origins = "*")
@Validated
public class CitizenController {
	
	@Autowired
	JwtUtils utils;
	@Autowired
	CitizenService citizenService;

	@Autowired
	BookingService bookingService;

	@Autowired
	AadharCardService aadharCardService;

	@Autowired
	CenterService centerService;

	@Autowired
	VaccinationRecordService vaccinationRecordService;

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	AuthenticationManager mgr;
	//Redone//Final
	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody @Valid CitizenSignInRequest request) {
		//return citizenService.authenticateCitizen(request);
		Authentication verifiedAuth=mgr.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		System.out.println(verifiedAuth.getDetails());
		System.out.println(verifiedAuth.getAuthorities());
		if(verifiedAuth.getAuthorities().iterator().next().getAuthority()!="CITIZEN") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(utils.generateJwtToken(verifiedAuth));
	}
	//Redone//Final
	@PostMapping("/register")
	public ResponseEntity<?> registerCitizen(@RequestBody @Valid RegisterCitizenForm form){
		Long aadharId=Long.parseLong(form.getAadharId());
		Citizen c=citizenService.findCitizenByAadharId(aadharId);
		if(c!=null)
			throw new RegistrationAlreadyDoneException(aadharId);
		String email=form.getEmail();
		String password=form.getPassword();
		String phoneNo=form.getPhoneNo();
		
		AadharCard aadharCard=aadharCardService.findById(aadharId).orElseThrow(()->new AadharCardNotFoundException(aadharId));
		Citizen citizen=new Citizen(email,aadharCard,phoneNo,password);
		CitizenDTO cDTO=citizenService.addNewCitizen(citizen);
		if(cDTO!=null)
			return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successfull");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration Failed");
	}
	//Redone//Final
	@GetMapping("/citizen_dashboard")
		public CitizenDTO getCitizenDetailsForDashboard() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return citizenService.getCitizenDetailsByEmail(auth.getName());
	}
	//Redone//Final
	@PostMapping("/centers/book")
	public ResponseEntity<String> bookVaccine(@RequestBody BookVaccineDTO request) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName().toString();
		Citizen citizen=citizenService.findCitizenByEmail(email);
		// Write Code To Throw Exception
		AadharCard aadhar = aadharCardService.findById(citizen.getAadharCard().getId())////dsasadd
				.orElseThrow(() -> new AadharCardNotFoundException(citizen.getAadharCard().getId().longValue()));
		Center center = centerService.findById(request.getCenterId()).orElseThrow(() -> new CenterNotFoundException());
		if (center.getStock() <= 0)
			throw new StockNotAvailableException();
		boolean bookedSlot = bookingService.bookVaccineSlot(aadhar, center);
		if (bookedSlot) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Booked Slot Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Occured While Booking Slot");
		}
	}
	//Redone//Final
	@GetMapping("/appointment_details")
	public ResponseEntity<AppointmentDetailsDTO> getAppointmentDetails(){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName().toString();
		Citizen citizen=citizenService.findCitizenByEmail(email);
		Booking booking=bookingService.getBookingByAadharId(citizen.getAadharCard().getId());
		String address=new StringBuilder().append(booking.getCenter().getCity())
				.append(", ")
				.append(booking.getCenter().getState())
				.append(", PINCODE:")
				.append(booking.getCenter().getPincode()).toString();
		AppointmentDetailsDTO appointmentDetails=new AppointmentDetailsDTO(booking.getAadharCard().getFirstName(),
				booking.getAadharCard().getLastName(),booking.getAadharCard().getId(),booking.getAadharCard().getGender(),
				booking.getCenter().getName(),address,booking.getDate());
		return ResponseEntity.status(HttpStatus.OK).body(appointmentDetails);
	}
	//Redone//Final
	@DeleteMapping("/appointment/cancel")
	public ResponseEntity<String> cancelAppointment() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName().toString();
		Citizen citizen=citizenService.findCitizenByEmail(email);
		System.out.println(citizen.getAadharCard().getId());
		System.out.println(citizen.getAadharCard().getId().getClass().getSimpleName());
		Booking booking = bookingService.getBookingByAadharId(citizen.getAadharCard().getId());
		System.out.println(booking.getId());
		bookingService.cancelAppointment(booking);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Cancelled Appointment Successfully");
	}
	//Rewrite//Done
	@GetMapping("/download_certificate")
	public ResponseEntity<VaccinationCertificate> downloadCertificate() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName().toString();
		Citizen citizen=citizenService.findCitizenByEmail(email);
		VaccinationCertificate certificate = new VaccinationCertificate();
		AadharCard card = aadharCardService.findById((citizen.getAadharCard().getId())).orElseThrow(() -> new AadharCardNotFoundException(citizen.getAadharCard().getId()));
		List<VaccinationRecord> listOfRecords = vaccinationRecordService.getVaccinationRecords((citizen.getAadharCard().getId()));
		if (listOfRecords.size() == 0)
			throw new ResourceNotFoundException("Unable to Fetch Certificate As No Vaccination Records Found");
		else if (listOfRecords.size() == 1)
			certificate.setVaccinationStatus("Partially Vaccinated (1/2)");
		else
			certificate.setVaccinationStatus("Fully Vaccinated (2/2)");
		certificate.setFirstName(card.getFirstName());
		certificate.setLastName(card.getLastName());
		certificate.setGender(card.getGender());
		certificate.setAadharId((citizen.getAadharCard().getId()));
		certificate.setAge((Period.between(card.getDob(), LocalDate.now())).getYears());
		return ResponseEntity.status(HttpStatus.OK).body(certificate);
	}
	//Rewrite Done
	@GetMapping(value = "/generate_qr_code", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] generateQRCode() throws IOException, WriterException {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String email=auth.getName().toString();
		Citizen citizen=citizenService.findCitizenByEmail(email);
		Long aadharId=citizen.getAadharCard().getId();
		VaccinationCertificate certificate = new VaccinationCertificate();
		AadharCard card = aadharCardService.findById(aadharId).orElseThrow(() -> new AadharCardNotFoundException(aadharId));
		List<VaccinationRecord> listOfRecords = vaccinationRecordService.getVaccinationRecords(aadharId);
		if (listOfRecords.size() == 0)
			throw new ResourceNotFoundException("Unable to Fetch Certificate As No Vaccination Records Found");
		else if (listOfRecords.size() == 1)
			certificate.setVaccinationStatus("Partially Vaccinated (1/2)");
		else
			certificate.setVaccinationStatus("Fully Vaccinated (2/2)");
		certificate.setFirstName(card.getFirstName());
		certificate.setLastName(card.getLastName());
		certificate.setGender(card.getGender());
		certificate.setAadharId(aadharId);
		certificate.setAge((Period.between(card.getDob(), LocalDate.now())).getYears());
		QRCodeGeneratorService qrCodeService = new QRCodeGeneratorService();
		BufferedImage qrImage = qrCodeService.generateQRCodeImage(certificate.toString(), 300, 300);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(qrImage, "png", byteArrayOutputStream);
		return byteArrayOutputStream.toByteArray();
	}
	//Rewrite Done
	@GetMapping("/statistics")
	public StatisticsDTO getStatistics(){
		StatisticsDTO stats=new StatisticsDTO();
		stats.setTotalVaccinations(vaccinationRecordService.getNumberOfRecords());
		stats.setTotalAppointments(bookingService.getNumberOfRecords());
		stats.setFemaleVaccinations(vaccinationRecordService.getNumberOfFemaleRecords());
		stats.setMaleVaccinations(vaccinationRecordService.getNumberOfMaleRecords());
		return stats;
	}
	
}
