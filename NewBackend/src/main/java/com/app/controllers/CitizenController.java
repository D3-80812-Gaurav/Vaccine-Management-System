package com.app.controllers;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.custom_exceptions.StockNotAvailableException;
import com.app.dtos.AppointmentDetailsDTO;
import com.app.dtos.BookVaccineDTO;
import com.app.dtos.CenterDTO;
import com.app.dtos.CitizenDTO;
import com.app.dtos.CitizenSignInRequest;
import com.app.dtos.StatisticsDTO;
import com.app.services.AadharCardService;
import com.app.services.BookingService;
import com.app.services.CenterService;
import com.app.services.CitizenService;
import com.app.services.QRCodeGeneratorService;
import com.app.services.VaccinationRecordService;
import com.google.zxing.WriterException;

import antlr.Utils;

import com.app.dtos.VaccinationCertificate;
import com.app.entities.AadharCard;
import com.app.entities.Booking;
import com.app.entities.Center;
import com.app.entities.VaccinationRecord;
import com.app.security.JwtUtils;

@RestController
@RequestMapping("/citizen")
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
	//CH
	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody @Valid CitizenSignInRequest request) {
		//return citizenService.authenticateCitizen(request);
		Authentication verifiedAuth=mgr.authenticate(new UsernamePasswordAuthenticationToken(request.getAadharID(), request.getPassword()));
		System.out.println(verifiedAuth.getDetails());
		System.out.println(verifiedAuth.getAuthorities());
		if(verifiedAuth.getAuthorities().iterator().next().getAuthority()!="CITIZEN") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(utils.generateJwtToken(verifiedAuth));
	}
	
	@GetMapping("/citizen_dashboard/{aadharId}")
		public CitizenDTO getCitizenById(@PathVariable Long aadharId) {
		
		return citizenService.getCitizenByAadharId((aadharId));
	}
	
	@GetMapping("/centers/{pinCode}")
	public List<CenterDTO> getCentersByPinCode(@PathVariable String pinCode) {
		return centerService.findByPinCodeAndStockAvailability(pinCode, 0).stream()
				.map(center -> mapper.map(center, CenterDTO.class)).collect(Collectors.toList());
	}

	@PostMapping("/centers/book")
	public ResponseEntity<String> bookVaccine(@RequestBody BookVaccineDTO request) {
		// Write Code To Throw Exception
		AadharCard aadhar = aadharCardService.findById(request.getAadharId())
				.orElseThrow(() -> new AadharCardNotFoundException());
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
	@GetMapping("appointment/{aadharId}")
	public ResponseEntity<AppointmentDetailsDTO> getAppointmentDetails(@PathVariable Long aadharId){
		Booking booking=bookingService.getBookingByAadharId(aadharId);
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
	

	@DeleteMapping("/appointment/cancel/{aadharId}")
	public ResponseEntity<String> cancelAppointment(@PathVariable Long aadharId) {
		System.out.println(aadharId);
		System.out.println(aadharId.getClass().getSimpleName());
		Booking booking = bookingService.getBookingByAadharId((aadharId));
		bookingService.cancelAppointment(booking);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Cancelled Appointment Successfully");
	}

	@PostMapping("/download_certificate")
	public ResponseEntity<VaccinationCertificate> downloadCertificate(@RequestBody Long aadharId) {
		VaccinationCertificate certificate = new VaccinationCertificate();
		AadharCard card = aadharCardService.findById((aadharId)).orElseThrow(() -> new AadharCardNotFoundException());
		List<VaccinationRecord> listOfRecords = vaccinationRecordService.getVaccinationRecords((aadharId));
		if (listOfRecords.size() == 0)
			throw new ResourceNotFoundException("Unable to Fetch Certificate As No Vaccination Records Found");
		else if (listOfRecords.size() == 1)
			certificate.setVaccinationStatus("Partially Vaccinated (1/2)");
		else
			certificate.setVaccinationStatus("Fully Vaccinated (2/2)");
		certificate.setFirstName(card.getFirstName());
		certificate.setLastName(card.getLastName());
		certificate.setGender(card.getGender());
		certificate.setAadharId((aadharId));
		certificate.setAge((Period.between(card.getDob(), LocalDate.now())).getYears());
		return ResponseEntity.status(HttpStatus.OK).body(certificate);
	}

	@PostMapping(value = "/generate_qr_code", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] generateQRCode(@RequestBody Long aadharId) throws IOException, WriterException {
		VaccinationCertificate certificate = new VaccinationCertificate();
		AadharCard card = aadharCardService.findById(aadharId).orElseThrow(() -> new AadharCardNotFoundException());
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