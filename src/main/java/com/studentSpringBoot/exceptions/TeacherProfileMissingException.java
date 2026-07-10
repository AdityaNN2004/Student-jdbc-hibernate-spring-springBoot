package com.studentSpringBoot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeacherProfileMissingException extends RuntimeException{
	public TeacherProfileMissingException(String message) {
		super(message);
	}
}
