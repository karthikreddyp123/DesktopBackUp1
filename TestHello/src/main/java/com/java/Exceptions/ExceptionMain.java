package com.java.Exceptions;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.java.Exceptions.CustomExceptions.InvalidAgeException;

public class ExceptionMain {
	static final Logger logger = Logger.getLogger(ExceptionMain.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("C:\\Users\\Karthik_Patlolla\\eclipse-workspace\\TestHello\\src\\main\\java\\com\\java\\Exceptions\\log4j.properties");
		int age=-100;
		try 
		{
			AgeValidator ageValidator=new AgeValidator();
			boolean isValid=ageValidator.isValidAge(age);
			if(isValid) {			
				System.out.println("Valid Age");
				logger.info("Valid Age");
			}
		}
		catch(InvalidAgeException ex) 
		{
			System.out.println("Error:"+ex.getMessage());
			logger.error(ex.getMessage());
		}
	}

}
