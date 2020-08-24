package com.epam.service;

public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = -5129209524082812279L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
