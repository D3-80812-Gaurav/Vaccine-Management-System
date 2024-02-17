package com.app.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.app.entities.Citizen;

public interface CitizenDao extends JpaRepository<Citizen, Long> {
	Optional<Citizen> findByPhoneNoAndPassword(String phoneNo, String password);

	@Query(nativeQuery = true, value = "select * from citizens where aadhar_id=:aadharId and password=:password")
	Optional<Citizen> findByAadharAndPassword(Long aadharId, String password);

	Optional<Citizen> findByAadharCardId(Long aadharId);
}
