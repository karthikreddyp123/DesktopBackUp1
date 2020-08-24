package com.example.demo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
	private static final org.slf4j.Logger logger=LoggerFactory.getLogger(Logger.class);
	@Before("execution(public * com.example.demo.HomeController.details())")
	public void log() {
		logger.info("Started.");
	}
	@AfterReturning("execution(public * com.example.demo.HomeController.details())")
	public void logAfter() {
		logger.info("Method executed succesfully.");
	}
	@AfterThrowing("execution(public * com.example.demo.HomeController.details())")
	public void logException() {
		logger.info("Exception occured.");
	}
	@After("execution(public * com.example.demo.HomeController.details())")
	public void logAfterFinally() {
		logger.info("Done.");
	}
}
