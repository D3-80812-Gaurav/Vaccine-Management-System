package com.app.custom_exceptions;
public class CenterNotFoundException extends RuntimeException{
	public CenterNotFoundException(){
		super("Center Associated With Provided Id Not Found");
	}
}