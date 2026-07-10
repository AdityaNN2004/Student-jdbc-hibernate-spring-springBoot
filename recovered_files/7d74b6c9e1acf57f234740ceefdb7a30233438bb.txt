package com.studentSpringBoot.exceptions;

public class DepartmentNotFoundException extends RuntimeException {
	public DepartmentNotFoundException(String message) {
		super(message);
	}
}
