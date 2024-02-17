package com.app.services;

import com.app.dtos.CitizenDTO;
import com.app.dtos.CitizenSignInRequest;


public interface CitizenService {
	public CitizenDTO authenticateCitizen(CitizenSignInRequest request);

	CitizenDTO getCitizenByAadharId(Long citizenId);
}
