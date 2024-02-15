package com.app.services;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.BookingNotFoundException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.custom_exceptions.StockNotAvailableException;
import com.app.dao.BookingDao;
import com.app.dao.CenterDao;
import com.app.entities.AadharCard;
import com.app.entities.Booking;
import com.app.entities.Center;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingDao bookingDao;

	@Autowired
	CenterDao centerDao;

	@Override
	@Transactional
	public boolean bookVaccineSlot(AadharCard aadharCard, Center center) {
		Booking booked = bookingDao.save(new Booking(aadharCard, center, LocalDate.now()));
		center.setStock(center.getStock() - 1);
		centerDao.save(center);
		return (booked != null) ? true : false;
	}

	@Override
	@Transactional
	public void cancelAppointment(Booking booking) {
		Center center = booking.getCenter();
		center.setStock(center.getStock()+1);
		centerDao.save(center);
		bookingDao.deleteByAadharCardId(booking.getAadharCard().getId());
	}

	@Override
	public Booking getBookingByAadharId(Long aadharId) {
		Booking booking = bookingDao.findByAadharCardId(aadharId).orElseThrow(() -> new BookingNotFoundException());
		return booking;
	}
}
