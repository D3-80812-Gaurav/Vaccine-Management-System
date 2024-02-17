package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.AadharCardDTO;
import com.app.dtos.CenterDTO;
import com.app.dtos.NewCenterRegistrationDTO;
import com.app.services.AadharCardService;
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
	
	@PostMapping("/register_new_aadhar_card")
	public ResponseEntity<String> addNewAadharCard(AadharCardDTO aadhar){
		boolean recordAdded=aadharCardService.addAadharCard(aadhar);
		if(recordAdded) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Added Successfully");
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable To Add Record");	
	}
	@PostMapping("/register_new_center")
	public ResponseEntity<String> addNewCenter(NewCenterRegistrationDTO center){
		ResponseEntity<String> centerAdded =centerService.addNewCenter(center);
		return centerAdded;	
	}
}
