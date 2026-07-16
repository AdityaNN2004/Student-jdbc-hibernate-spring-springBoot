package com.studentSpringBoot.dto.response;

import com.studentSpringBoot.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
	private String token;
	private String email;
	private UserRole role;
	
}
