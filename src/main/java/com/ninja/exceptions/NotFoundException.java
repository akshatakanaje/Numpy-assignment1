package com.ninja.exceptions;


public class NotFoundException extends RuntimeException {	
	
	private static final long serialVersionUID = -5151203246620341117L;
	
	public NotFoundException(String message) {
		super(message);
	}

}
