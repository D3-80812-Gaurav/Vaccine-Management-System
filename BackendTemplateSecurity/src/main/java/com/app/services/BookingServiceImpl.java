package com.app.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.custom_exceptions.BookingNotFoundException;
import com.app.dao.BookingDao;
import com.app.dao.CenterDao;
import com.app.dtos.BookingDTO;
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
	public Booking cancelAppointment(Booking booking) {
		Center center = booking.getCenter();
		center.setStock(center.getStock() + 1);
		centerDao.save(center);
		Booking deleted=bookingDao.deleteByAadharCardId(booking.getAadharCard().getId());
		if(deleted!=null)
			return deleted;
		else
			return null;
	}

	@Override
	public Booking getBookingByAadharId(Long aadharId) {
		Booking booking = bookingDao.findByAadharCardId(aadharId).orElseThrow(() -> new BookingNotFoundException());
		return booking;
	}

	@Override
	public List<BookingDTO> getBookingsByCenterId(Long centerId) {
		List<BookingDTO> bookings = bookingDao.findByCenterId(centerId).stream()
				.map(booking -> new BookingDTO(booking.getAadharCard().getFirstName(),
						booking.getAadharCard().getLastName(), booking.getAadharCard().getId(), booking.getDate()))
				.collect(Collectors.toList());
		return bookings;
	}

	@Override
	public Long getNumberOfRecords() {
		return bookingDao.count();
	}
}
