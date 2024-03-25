package com.app.services;

import java.util.Optional;

import com.app.dtos.AadharCardDTO;
import com.app.entities.AadharCard;

public interface AadharCardService {

	Optional<AadharCard> findById(Long aadharId);

	AadharCard addAadharCard(AadharCardDTO aadhar);

}
