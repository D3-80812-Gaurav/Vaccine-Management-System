package com.app.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exceptions.AadharCardNotFoundException;
import com.app.custom_exceptions.CenterNotFoundException;
import com.app.dtos.AadharCardDTO;
import com.app.dtos.AadharDTO;
import com.app.dtos.AdminSignInRequest;
import com.app.dtos.CenterDTO;
import com.app.dtos.NewCenterRegistrationDTO;
import com.app.entities.AadharCard;
import com.app.entities.Center;
import com.app.security.JwtUtils;
import com.app.services.AadharCardService;
import com.app.services.AdminService;
import com.app.services.CenterService;
import com.app.services.QRCodeGeneratorService;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class AdminController {
	@Autowired
	AadharCardService aadharCardService;

	@Autowired
	CenterService centerService;

	@Autowired
	AdminService adminService;

	@Autowired
	JwtUtils utils;

	@Autowired
	AuthenticationManager mgr;

	@Autowired
	private ModelMapper mapper;

	@PostMapping("/register_new_aadhar_card")
	public ResponseEntity<AadharDTO> addNewAadharCard(@RequestBody @Valid AadharCardDTO aadhar) {
		AadharCard card = aadharCardService.addAadharCard(aadhar);
		AadharDTO response = mapper.map(card, AadharDTO.class);
		if (card != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@PostMapping("/register_new_center")
	public ResponseEntity<String> addNewCenter(@RequestBody @Valid NewCenterRegistrationDTO center) {
		System.out.println(center.getCity());
		ResponseEntity<String> centerAdded = centerService.addNewCenter(center);
		return centerAdded;
	}

	@PostMapping("/signin")
	public ResponseEntity<String> adminLogin(@RequestBody @Valid AdminSignInRequest request) {
		Authentication verifiedAuth = mgr
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		System.out.println(verifiedAuth.getDetails());
		System.out.println(verifiedAuth.getAuthorities());
		if (verifiedAuth.getAuthorities().iterator().next().getAuthority() != "ADMIN") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(utils.generateJwtToken(verifiedAuth));
	}

	@GetMapping("/get_aadhar_card/{aadharId}")
	public ResponseEntity<AadharDTO> findAadharCard(@PathVariable String aadharId) {
		AadharCard card = aadharCardService.findById(Long.parseLong(aadharId))
				.orElseThrow(() -> new AadharCardNotFoundException(Long.parseLong(aadharId)));
		AadharDTO dto = mapper.map(card, AadharDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/get_center_details/{centerId}")
	public ResponseEntity<CenterDTO> findCenter(@PathVariable String centerId) {
		Center center = centerService.findById(Long.parseLong(centerId))
				.orElseThrow(() -> new CenterNotFoundException((centerId)));
		CenterDTO dto = mapper.map(center, CenterDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	@PatchMapping("/modify_center")
	public ResponseEntity<CenterDTO> findCenter(@RequestBody CenterDTO request) {
		Center center = centerService.findById((request.getId()))
				.orElseThrow(() -> new CenterNotFoundException((request.getId().toString())));
		center.setName(request.getName());
		center.setCity(request.getCity());
		center.setState(request.getState());
		center.setPincode(request.getPincode());
		center.setStock(request.getStock());
		centerService.updateCenter(center);
		CenterDTO dto = mapper.map(center, CenterDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@GetMapping(value = "/get_aadhar_qr/{aadharId}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] generateAadharQRCode(@PathVariable String aadharId) throws IOException, WriterException {
		AadharCard card = aadharCardService.findById(Long.parseLong(aadharId))
				.orElseThrow(() -> new AadharCardNotFoundException(Long.parseLong(aadharId)));
		AadharDTO dto=mapper.map(card, AadharDTO.class);
		QRCodeGeneratorService qrCodeService = new QRCodeGeneratorService();
		BufferedImage qrImage = qrCodeService.generateQRCodeImage(dto.toString(), 300, 300);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(qrImage, "png", byteArrayOutputStream);
		return byteArrayOutputStream.toByteArray();
	}
	
}
