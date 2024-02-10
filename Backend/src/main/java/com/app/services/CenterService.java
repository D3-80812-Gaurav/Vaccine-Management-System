package com.app.services;

import java.util.List;

import com.app.dtos.CenterDTO;

public interface CenterService {
	List<CenterDTO> getAllCentersByPinCode(String pincode);
}
