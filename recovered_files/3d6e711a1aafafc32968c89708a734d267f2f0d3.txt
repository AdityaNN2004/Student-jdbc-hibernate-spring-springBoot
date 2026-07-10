package com.studentSpringBoot.exceptions;

public class RollNumberAlreadyExistsException extends RuntimeException {
	public RollNumberAlreadyExistsException(String message) {
		super(message);
	}
}
