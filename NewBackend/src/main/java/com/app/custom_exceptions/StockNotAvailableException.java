package com.app.custom_exceptions;

public class StockNotAvailableException extends RuntimeException{
	public StockNotAvailableException(){
		super("Vaccine Stock is Not Available");
	}
}
