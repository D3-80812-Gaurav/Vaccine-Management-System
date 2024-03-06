package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.app.dtos.AdminSignInRequest;
import com.app.dtos.ApiResponse;
import com.app.dtos.CenterDTO;
import com.app.dtos.NewCenterRegistrationDTO;
import com.app.entities.Center;

public interface CenterService {
	List<CenterDTO> getAllCentersByPinCode(String pincode);

	Optional<Center> findById(Long centerId);
	
	List<Center> findByPinCodeAndStockAvailability(String pincode, int stock);

	ResponseEntity<String> addNewCenter(NewCenterRegistrationDTO center);

	CenterDTO findByIdAndPassword(Long centerId, String password);

}
