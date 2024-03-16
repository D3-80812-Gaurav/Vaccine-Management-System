package com.app.custom_exceptions;
@SuppressWarnings("serial")
public class CenterNotFoundException extends RuntimeException{
	public CenterNotFoundException(){
		super("Center Associated With Provided Id Not Found");
	}

	public CenterNotFoundException(String string) {
		super(string);
	}
}