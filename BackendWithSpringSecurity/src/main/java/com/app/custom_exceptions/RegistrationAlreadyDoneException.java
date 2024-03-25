package com.app.custom_exceptions;

@SuppressWarnings("serial")
public class RegistrationAlreadyDoneException extends RuntimeException {
	public RegistrationAlreadyDoneException(Long aadharId) {
		super("You Have Already Registered With Provided AadharID:"+aadharId);
	}
}
