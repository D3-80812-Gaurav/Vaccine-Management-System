package com.app.services;

import java.util.List;
import java.util.Optional;

import com.app.dtos.CenterDTO;
import com.app.dtos.NewCenterRegistrationDTO;
import com.app.entities.Center;

public interface CenterService {
	List<CenterDTO> getAllCentersByPinCode(String pincode);

	Optional<Center> findById(Long centerId);
	
	List<Center> findByPinCodeAndStockAvailability(String pincode, int stock);

	boolean addNewCenter(NewCenterRegistrationDTO center);
	
}
