package com.app.custom_exceptions;

@SuppressWarnings("serial")
public class BookingNotFoundException extends RuntimeException{
	public BookingNotFoundException(){
		super("Booking Associated With Provided Id Not Found");
	}
}
