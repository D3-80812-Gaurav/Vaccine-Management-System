//package com.app.dao;
//
//import java.util.Locale;
//import java.util.Random;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//
//import com.app.entities.AadharCard;
//import com.app.entities.Citizen;
//import com.github.javafaker.Faker;
//
//@DataJpaTest
//@Rollback(false)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//public class CitizenDaoTest {
//
//	@Autowired
//	CitizenDao dao;
//	
//	@Autowired
//	PasswordEncoder encoder;
//
//	@Test
//	public void addCitizens() {
//		Faker faker = new Faker(new Locale("en-IND"));
//		for (Long i = 0L; i < 50; i++) {
//			AadharCard aadhar = new AadharCard((Long) (i + 1));
//			long phoneNumber = 9090909090L;
//			String formattedNumber = String.format("%010d", phoneNumber+i);
//			Citizen citizen = new Citizen(aadhar, formattedNumber, encoder.encode("abc"));
//			dao.save(citizen);
//		}
//	}
//}
