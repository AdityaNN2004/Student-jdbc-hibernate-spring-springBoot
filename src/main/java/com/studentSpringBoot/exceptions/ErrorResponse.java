package com.studentSpringBoot.exceptions;

import java.time.LocalDateTime;

//import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ErrorResponse {
	private LocalDateTime timestamp;
	private int httpStatus;
	private String error;
	private String message;
	private String path;
}
