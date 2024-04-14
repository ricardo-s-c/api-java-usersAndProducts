package com.apijavalogin.apijavalogin.services.excepitions;

public class ForbiddenException extends RuntimeException{
	
	public ForbiddenException(String msg) {
		super(msg);
	}
}
