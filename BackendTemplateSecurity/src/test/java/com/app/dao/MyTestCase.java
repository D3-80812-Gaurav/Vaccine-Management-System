//package com.app.dao;
//
//import java.time.LocalDate;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//
//import com.app.custom_exceptions.ResourceNotFoundException;
//import com.app.entities.AadharCard;
//import com.app.entities.Booking;
//import com.app.entities.Center;
//import com.app.entities.Citizen;
//import com.app.entities.Gender;
//import com.app.entities.VaccinationRecord;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
//public class MyTestCase {
//	@Autowired
//	AadharCardDao aadharCardDao;
//	
//	@Autowired
//	CenterDao centerDao;
//	
//	@Autowired
//	BookingDao bookingDao;
//	
//	@Autowired
//	CitizenDao citizenDao;
//	
//	@Autowired
//	ReviewDao reviewDao;
//	
//	@Autowired
//	VaccinationRecordDao vaccinationRecordDao;
//	
//	@Test
//	public void entireAppTest() {
//		
//		PasswordEncoder encoder=new BCryptPasswordEncoder();
//		//Registering AadharCards
//		AadharCard gaurav=new AadharCard("Gaurav","Ghenand",LocalDate.parse("1998-06-15"),Gender.MALE,"Mahrashtra","Pune","411043");
//		aadharCardDao.save(gaurav);
//		
//		//Registering Citizens
//		Citizen appaCitizen=new Citizen(gaurav,"9665105760",encoder.encode("Gaurav@123"));
//		citizenDao.save(appaCitizen);
//		
//		Center empty=centerDao.findById(21L).orElseThrow(()->new ResourceNotFoundException("Center Not Found"));
//		vaccinationRecordDao.save(new VaccinationRecord(empty,null,gaurav,LocalDate.parse("2020-01-05")));
//	}
//}
