//package com.app.dao;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//
//import com.app.entities.Admin;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
//public class AdminDaoTest {
//	@Autowired
//	AdminDao aDao;
//	
//	@Autowired
//	PasswordEncoder encoder;
//	
//	@Test
//	void testAddAdmin() {
//		Admin admin=new Admin("King",encoder.encode("Kong"));
//		aDao.save(admin);
//	}
//	
//}
