package com.apijavalogin.apijavalogin.services.excepitions;

public class UnauthorizedException extends RuntimeException{
	
	public UnauthorizedException(String msg) {
		super(msg);
	}
}

