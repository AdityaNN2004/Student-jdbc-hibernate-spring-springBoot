package com.studentSpringBoot.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AuthRequestDto {
	@NotBlank(message = "Email is Required")
	@Email(message = "Invalid Email format")
	private String email;
	@NotBlank(message ="Password is Required")
//	@Pattern(regexp = "((?=.*\\d),(?=.*[a-z]),(?=.*[#$@*].{5,20}))",message="Invalid Password Format")
	private String password;
}
