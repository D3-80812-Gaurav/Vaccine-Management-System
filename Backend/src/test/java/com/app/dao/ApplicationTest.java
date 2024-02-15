package com.app.dao;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.AadharCard;
import com.app.entities.Booking;
import com.app.entities.Center;
import com.app.entities.Citizen;
import com.app.entities.Gender;
import com.app.entities.VaccinationRecord;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ApplicationTest {

	@Autowired
	AadharCardDao aadharCardDao;
	
	@Autowired
	CenterDao centerDao;
	
	@Autowired
	BookingDao bookingDao;
	
	@Autowired
	CitizenDao citizenDao;
	
	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	VaccinationRecordDao vaccinationRecordDao;
	
	@Test
	public void entireAppTest() {
		//Registering AadharCards
		AadharCard appa=new AadharCard("Abhishek","Kognoor",LocalDate.parse("1995-01-01"),Gender.MALE,"Karnataka","Belgaon","Kokatnoor","411001");
		aadharCardDao.save(appa);
		
		AadharCard niraj=new AadharCard("Niraj","Zamabre",LocalDate.parse("2000-12-28"),Gender.MALE,"Maharashtra","Mumbai","Ulhasnagar","411041");
		aadharCardDao.save(niraj);
		
		AadharCard ganesh=new AadharCard("Ganesh","Yadav",LocalDate.parse("2001-04-01"),Gender.MALE,"Maharashtra","Sambhajinagar","Nagar","411009");
		aadharCardDao.save(ganesh);
		
		//Registering Citizens
		Citizen appaCitizen=new Citizen(appa,"7218327960","Appa@123");
		citizenDao.save(appaCitizen);
		
		Citizen nirajCitizen=new Citizen(niraj,"7766554433","Niraj@123");
		citizenDao.save(nirajCitizen);
		
		Citizen ganeshCitizen=new Citizen(ganesh,"9876543210","Ganesh@123");
		citizenDao.save(ganeshCitizen);

		//Adding Centers
		Center sunbeam=new Center("Sunbeam Vaccination Point", "Maharashtra", "Pune","Pimpri-Chinchwad","411057", 240, "Sunbeam#123");
		centerDao.save(sunbeam);
		
		Center iacsd=new Center("IACSD Vaccination Point", "Maharashtra", "Pune","Akurdi","411005", 120, "Hacker#123");
		centerDao.save(iacsd);
		
		Center main=new Center("Main Vaccination Point", "Maharashtra", "Pune","Aundh","411003", 7, "Main#123");
		centerDao.save(main);
		
		Center empty=new Center("Empty Vaccination Point", "Maharashtra", "Pune","Aundh","411003", 0, "Main#123");
		centerDao.save(empty);
		
		//Adding Vaccination Records;
		//Appa is fully Vaccinated
		vaccinationRecordDao.save(new VaccinationRecord(sunbeam,null,appa,LocalDate.parse("2020-01-05")));
		vaccinationRecordDao.save(new VaccinationRecord(sunbeam,null,appa,LocalDate.parse("2021-01-05")));
		//Niraj is Partially Vaccinated and Havent booked any appointments
		vaccinationRecordDao.save(new VaccinationRecord(iacsd,null,niraj,LocalDate.parse("2021-01-17")));
		//Ganesh is Unvaccinated But Has Booked an Appointment
		bookingDao.save(new Booking(ganesh,main,LocalDate.now()));
	}
}
