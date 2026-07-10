package com.studentSpringBoot.exceptions;

@SuppressWarnings("serial")
public class ApiException extends RuntimeException {
	public ApiException(String message) {
		super(message);
	}
}
