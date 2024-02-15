package com.app.custom_exceptions;

public class AadharCardNotFoundException extends RuntimeException{
	public AadharCardNotFoundException(){
		super("Aadhar Card Associated With Provided Id Not Found");
	}
}