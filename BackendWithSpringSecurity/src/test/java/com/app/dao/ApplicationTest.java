package com.app.dao;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.app.entities.AadharCard;
import com.app.entities.Admin;
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
	AdminDao adminDao;

	@Autowired
	VaccinationRecordDao vaccinationRecordDao;

	@Autowired
	PasswordEncoder encoder;

	@Test
	public void entireAppTest() {
		// Registering AadharCards
		AadharCard appa = new AadharCard("Abhishek", "Kognoor", LocalDate.parse("1995-01-01"), Gender.MALE, "Karnataka",
				"Kokatnoor", "411001");
		aadharCardDao.save(appa);

		AadharCard niraj = new AadharCard("Niraj", "Zamabre", LocalDate.parse("2000-12-28"), Gender.MALE, "Maharashtra",
				"Ulhasnagar", "411041");
		aadharCardDao.save(niraj);

		AadharCard ganesh = new AadharCard("Ganesh", "Yadav", LocalDate.parse("2001-04-01"), Gender.MALE, "Maharashtra",
				"Nagar", "411009");
		aadharCardDao.save(ganesh);

		// Registering Citizens
		Citizen appaCitizen = new Citizen("appa@gmail.com", appa, "7218327960", encoder.encode("Appa@123"));
		citizenDao.save(appaCitizen);

		Citizen nirajCitizen = new Citizen("niraj@gmail.com", niraj, "7766554433", encoder.encode("Appa@123"));
		citizenDao.save(nirajCitizen);

		Citizen ganeshCitizen = new Citizen("ganesh@gmail.com", ganesh, "9876543210", encoder.encode("Appa@123"));
		citizenDao.save(ganeshCitizen);

		// Adding Centers
		Center sunbeam = new Center("Sunbeam Vaccination Point", "Maharashtra", "Pimpri-Chinchwad", "411057", 240,
				encoder.encode("Center@123"), "sunbeam@center.com");
		centerDao.save(sunbeam);

		Center iacsd = new Center("IACSD Vaccination Point", "Maharashtra", "Akurdi", "411005", 120,
				encoder.encode("Center@123"), "iacsd@center.com");
		centerDao.save(iacsd);

		Center main = new Center("Main Vaccination Point", "Maharashtra", "Aundh", "411003", 7,
				encoder.encode("Center@123"), "main@center.com");
		centerDao.save(main);

		Center empty = new Center("Empty Vaccination Point", "Maharashtra", "Aundh", "411003", 0,
				encoder.encode("Center@123"), "empty@center.com");
		centerDao.save(empty);

		Admin admin = new Admin("king@admin.com", "King", encoder.encode("Kong"));
		adminDao.save(admin);
		// Adding Vaccination Records;
		// Appa is fully Vaccinated
		vaccinationRecordDao.save(new VaccinationRecord(sunbeam, null, appa, LocalDate.parse("2020-01-05")));
		vaccinationRecordDao.save(new VaccinationRecord(sunbeam, null, appa, LocalDate.parse("2021-01-05")));
		// Niraj is Partially Vaccinated and Havent booked any appointments
		vaccinationRecordDao.save(new VaccinationRecord(iacsd, null, niraj, LocalDate.parse("2021-01-17")));
		// Ganesh is Unvaccinated But Has Booked an Appointment
		bookingDao.save(new Booking(ganesh, main, LocalDate.now()));
	}
}
