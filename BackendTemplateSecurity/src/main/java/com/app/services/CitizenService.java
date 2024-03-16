package com.app.services;

import com.app.dtos.CitizenDTO;
import com.app.entities.Citizen;
public interface CitizenService {
	CitizenDTO getCitizenDetailsByEmail(String email);

	CitizenDTO addNewCitizen(Citizen citizen);

	Citizen findCitizenByEmail(String email);
}
