package com.app.custom_exceptions;

@SuppressWarnings("serial")
public class CitizenNotFoundException extends RuntimeException {
	public CitizenNotFoundException(String email) {
		super("Citizen associated with email: "+email+" not found");
	}
}
