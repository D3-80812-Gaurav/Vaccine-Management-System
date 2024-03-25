package com.app.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AadharCardDao;
import com.app.dtos.AadharCardDTO;
import com.app.entities.AadharCard;

@Service
@Transactional
public class AadharCardServiceImpl implements AadharCardService{

	@Autowired
	AadharCardDao aadharCardDao;

	@Override
	public Optional<AadharCard> findById(Long aadharId) {
		return aadharCardDao.findById(aadharId);
	}

	@Override
	public AadharCard addAadharCard(AadharCardDTO a) {
		AadharCard card=aadharCardDao.save(new AadharCard(a.getFirstName(),a.getLastName(),a.getDob(),a.getGender(),a.getState(),a.getCity(),a.getPinCode()));
		if(card!=null)
			return card;
		else
			return null;
	}
}
