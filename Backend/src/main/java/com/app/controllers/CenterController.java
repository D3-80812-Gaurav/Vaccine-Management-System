package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.CenterDTO;
import com.app.services.CenterService;

@RestController
@RequestMapping("/centers")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CenterController {

	@Autowired
	private CenterService centerService;

	@GetMapping("/{centerID}")
	public List<CenterDTO> getCentersByPinCode(@PathVariable String centerID) {
		return centerService.getAllCentersByPinCode(centerID);
	}
}
