package com.app.dao;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.AadharCard;
import com.app.entities.Gender;
import com.github.javafaker.Faker;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AadharCardDaoTest {
	@Autowired
	private AadharCardDao dao;

	@Test
	void testSaveAadharCards() {
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			Faker faker = new Faker(new Locale("en-IND"));

			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			LocalDate dob = LocalDate.of(2000 + random.nextInt(20), random.nextInt(12) + 1, random.nextInt(28) + 1);
			Gender gender = random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
			String state = faker.address().state();
			String city = faker.address().city();
			String locality = faker.address().streetName();
			String zipcode = faker.address().zipCode();

			AadharCard card = new AadharCard(name, surname, dob, gender, state, locality, zipcode);
			dao.save(card);
		}
	}

}
