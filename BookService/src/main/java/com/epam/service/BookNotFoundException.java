package com.epam.service;

public class BookNotFoundException extends Exception{
	private static final long serialVersionUID = 6417194887856281035L;

	public BookNotFoundException(String s) {
		super(s);
	}
}
