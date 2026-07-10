package com.studentSpringBoot.dto.response;

import com.studentSpringBoot.entity.UserRole;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {
	private String firstName;
	private String lastName;
	private String email;
	private String dob;
	private String mobile;
	private UserRole userRole;
}
