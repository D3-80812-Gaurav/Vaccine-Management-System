package com.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dtos.AadharCardDTO;
import com.app.dtos.AdminSignInRequest;
import com.app.dtos.ApiResponse;
import com.app.dtos.CenterDTO;
import com.app.dtos.NewCenterRegistrationDTO;
import com.app.entities.Admin;
import com.app.services.AadharCardService;
import com.app.services.AdminService;
import com.app.services.CenterService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class AdminController {
	@Autowired
	AadharCardService aadharCardService;
	
	@Autowired
	CenterService centerService;
	
	@Autowired
	AdminService adminService;
		
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
	
	@PostMapping("/login")
	public ResponseEntity<String> adminLogin(@RequestBody @Valid AdminSignInRequest request) {
		Admin admin=adminService.findByNameAndPassword(request.getName(), request.getPassword())
				.orElseThrow(()->new ResourceNotFoundException("Admin Credentials Not Valid"));
		if(admin!=null)
			return ResponseEntity.status(HttpStatus.OK).body("Login Successfull");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login Failed");	
	}
}
