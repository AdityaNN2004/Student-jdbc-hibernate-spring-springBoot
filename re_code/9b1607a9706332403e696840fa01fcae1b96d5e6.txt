package com.studentSpringBoot.exceptions;

@SuppressWarnings("serial")
public class StudentProfileMissingException extends RuntimeException {
	public StudentProfileMissingException(String message) {
		super(message);
	}
}
