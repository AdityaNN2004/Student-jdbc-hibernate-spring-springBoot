package com.studentSpringBoot.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentSignUpRequestDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobileNo;
	private String rollNo;
	private LocalDate dob;
	private String address;
	private String password;
}
