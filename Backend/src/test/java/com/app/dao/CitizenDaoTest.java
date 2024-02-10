package com.app.dao;

import java.util.Locale;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.AadharCard;
import com.app.entities.Citizen;
import com.github.javafaker.Faker;

@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CitizenDaoTest {

	@Autowired
	CitizenDao dao;

	@Test
	public void addCitizens() {
		Random random = new Random();
		Faker faker = new Faker(new Locale("en-IND"));
//		for (Long i = 0L; i < 50; i++) {
//			AadharCard aadhar = new AadharCard((Long) (i + 1));
//			long phoneNumber = random.nextLong(1_000_000_000L, 9_999_999_999L);
//	        String formattedNumber = String.format("%010d", phoneNumber);
//			Citizen citizen = new Citizen(aadhar,formattedNumber,faker.cat().name());
//			dao.save(citizen);
//		}
		AadharCard aadhar = new AadharCard((51L));
		long phoneNumber = random.nextLong(1_000_000_000L, 9_999_999_999L);
		String formattedNumber = String.format("%010d", phoneNumber);
		Citizen citizen = new Citizen(aadhar, formattedNumber, faker.cat().name());
		dao.save(citizen);
	}
}
