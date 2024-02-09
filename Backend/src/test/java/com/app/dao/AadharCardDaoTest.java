package com.app.dao;

import java.time.LocalDate;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AadharCardDaoTest {
	@Autowired
	private AadharCardDao dao;
	
	@Test
	void testSaveAadharCards() {
		AadharCard a1=new AadharCard("Papu"
				,"Bhaiya"
				,LocalDate.parse("2024-01-01")
				,Gender.MALE,"Maharashtra","Pune","Katraj","41104");
		Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = RandomStringUtils.randomAlphabetic(6);
            String surname = RandomStringUtils.randomAlphabetic(8);
            LocalDate dob = LocalDate.of(2000 + random.nextInt(20), random.nextInt(12) + 1, random.nextInt(28) + 1);
            Gender gender = random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
            String state = RandomStringUtils.randomAlphabetic(8);
            String city = RandomStringUtils.randomAlphabetic(10);
            String locality = RandomStringUtils.randomAlphabetic(12);
            String pincode = RandomStringUtils.randomNumeric(6);

            AadharCard card = new AadharCard(name, surname, dob, gender, state, city, locality, pincode);
            dao.save(card);
        }
	}

}

//@Column(length=20)
//private String firstName;
//@Column(length=20)
//private String lastName;
//private LocalDate dob;
//@Enumerated(EnumType.STRING)
//@Column(length=10)
//private Gender gender;
//@Column(length=20)
//private String state;
//@Column(length=20)
//private String district;
//@Column(length=20)
//private String city;
//@Column(length = 6, name = "pin_code")
//private String pinCode;
