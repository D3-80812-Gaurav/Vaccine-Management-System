package com.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.dao.CenterDao;
import com.app.dtos.CenterDTO;
import com.app.dtos.NewCenterRegistrationDTO;
import com.app.entities.Center;

@Service
@Transactional
public class CenterServiceImpl implements CenterService {

	@Autowired
	private CenterDao cDao;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<CenterDTO> getAllCentersByPinCode(String pincode) {
		List<CenterDTO> centers = cDao.findByPincode(pincode).stream()
				.map(center -> mapper.map(center, CenterDTO.class)).collect(Collectors.toList());
		return centers;
	}

	@Override
	public Optional<Center> findById(Long centerId) {
		// TODO Auto-generated method stub
		return cDao.findById(centerId);
	}

	@Override
	public ResponseEntity<String> addNewCenter(NewCenterRegistrationDTO center) {
		//String name, String state, String district, String city, String pinCode, int stock, String password
		Center createdCenter=cDao.save(new Center(center.getName(),center.getState(),center.getCity(),center.getPinCode(),center.getStock(),encoder.encode(center.getPassword()),center.getEmail()));
		if(createdCenter!=null)
			return ResponseEntity.status(HttpStatus.OK).body("Added New Center");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to Add Center");
	}

	@Override
	public List<Center> findByPinCodeAndStockAvailability(String pincode, int stock) {
		List<Center>centers=cDao.findByPincodeAndStockGreaterThan(pincode, stock);
		return centers;
	}

	@Override
	public Optional<Center> findByEmail(String email) {
		return cDao.findByEmail(email);
	}

	@Override
	public Center updateCenter(Center center) {
		Center updated=cDao.save(center);
		return updated;
	}
}
