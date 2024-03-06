package com.app.custom_exceptions;

public class BookingNotFoundException extends RuntimeException{
	public BookingNotFoundException(){
		super("Booking Associated With Provided Id Not Found");
	}
}
