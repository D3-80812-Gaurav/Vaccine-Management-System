//package com.app.dao;
//
//import java.time.LocalDate;
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
//import com.app.entities.Center;
//import com.app.entities.Gender;
//import com.github.javafaker.Faker;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
//public class CenterDaoTest {
//
//	@Autowired
//	CenterDao dao;
//	
//	@Autowired
//	PasswordEncoder encoder;
//
//	@Test
//	public void addCentersTest() {
//		for (int i = 0; i < 10; i++) {
//			Random random = new Random();
//			Faker faker = new Faker(new Locale("en-IND"));
//
//			String name = faker.pokemon().name() + " Vaccination Point";
//			String state = faker.address().state();
//			String city = faker.address().cityName();
//			String pincode = faker.address().zipCode();
//			int stock = random.nextInt(100);
//			String password = encoder.encode(faker.cat().name());
//
//			Center center = new Center(name, state, city, pincode, stock, password);
//			dao.save(center);
//		}
//	}
//}
