package com.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dtos.AadharCardDTO;
import com.app.dtos.AdminSignInRequest;
import com.app.dtos.NewCenterRegistrationDTO;
import com.app.security.JwtUtils;
import com.app.services.AadharCardService;
import com.app.services.AdminService;
import com.app.services.CenterService;

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
		
	@PostMapping("/register_new_aadhar_card")
	public ResponseEntity<String> addNewAadharCard(@RequestBody @Valid AadharCardDTO aadhar){
		boolean recordAdded=aadharCardService.addAadharCard(aadhar);
		if(recordAdded) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Added Successfully");
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable To Add Record");	
	}
	@PostMapping("/register_new_center")
	public ResponseEntity<String> addNewCenter(@RequestBody @Valid NewCenterRegistrationDTO center){
		
		System.out.println(center.getCity());
		ResponseEntity<String> centerAdded =centerService.addNewCenter(center);
		return centerAdded;	
	}
	
	@PostMapping("/signin")
	public ResponseEntity<String> adminLogin(@RequestBody @Valid AdminSignInRequest request) {
		Authentication verifiedAuth=mgr.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		System.out.println(verifiedAuth.getDetails());
		System.out.println(verifiedAuth.getAuthorities());
		if(verifiedAuth.getAuthorities().iterator().next().getAuthority()!="ADMIN") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(utils.generateJwtToken(verifiedAuth));	
	}
}
