package com.app.services;

import com.app.entities.AadharCard;
import com.app.entities.Booking;
import com.app.entities.Center;

public interface BookingService {
	boolean bookVaccineSlot(AadharCard aadharCard, Center center);

	void cancelAppointment(Booking booking);
	
	Booking getBookingByAadharId(Long aadharId);
}
