package com.app.custom_exceptions;

@SuppressWarnings("serial")
public class AadharCardNotFoundException extends RuntimeException{
	public AadharCardNotFoundException(Long aadharId) {
		super("Aadhar Card Associated With Provided Id "+aadharId+" Not Found");
	}
}