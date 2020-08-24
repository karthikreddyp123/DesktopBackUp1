package com.java.Exceptions;

import com.java.Exceptions.CustomExceptions.InvalidAgeException;

public class AgeValidator {
	public boolean isValidAge(int Age) throws InvalidAgeException{
		if(Age<0) {
			throw new InvalidAgeException("Invalid Age, Please Enter Again");
		}
		return true;
	}
}
