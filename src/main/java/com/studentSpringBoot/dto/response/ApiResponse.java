package com.studentSpringBoot.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ApiResponse {
	private String message;
	private String status;
	private LocalDateTime timeStamp;
	public ApiResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
		this.timeStamp = LocalDateTime.now();
	}
	
}
