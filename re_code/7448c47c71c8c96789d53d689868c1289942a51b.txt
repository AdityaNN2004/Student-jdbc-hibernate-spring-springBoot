package com.studentSpringBoot.exceptions;

@SuppressWarnings("serial")
public class EnrollmentNotFoundException extends RuntimeException{
	public EnrollmentNotFoundException(String message) {
		super(message);
	}
}
