package com.studentSpringBoot.dto.response;

import com.studentSpringBoot.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
public class AuthResponse {
	private Long id;
	private String email;
	private UserRole userRole;
	private boolean isAuthenticated=false;
}
