package com.java.Exceptions.CustomExceptions;

public class InvalidAgeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAgeException(String s) {
		super(s);
	}
}
