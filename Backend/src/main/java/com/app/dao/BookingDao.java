package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.AadharCard;
import com.app.entities.Booking;
import java.util.Optional;

public interface BookingDao extends JpaRepository<Booking, Long> {

	Optional<Booking> findByAadharCard(AadharCard a);

	@Modifying
	@Query("delete from Booking b where b.aadharCard.id=:aadhar_id")
	int deleteByAadharCardId(Long aadhar_id);

	Optional<Booking> findByAadharCardId(Long aadharId);
}
