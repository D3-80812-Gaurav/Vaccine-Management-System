package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CenterDao;
import com.app.dtos.CenterDTO;

@Service
@Transactional
public class CenterServiceImpl implements CenterService {

	@Autowired
	private CenterDao cDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<CenterDTO> getAllCentersByPinCode(String pincode) {
		List<CenterDTO> centers = cDao.findByPinCode(pincode).stream()
				.map(center -> mapper.map(center, CenterDTO.class)).collect(Collectors.toList());
		return centers;
	}

}
