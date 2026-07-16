package com.studentSpringBoot.service;

import com.studentSpringBoot.dto.request.AuthRequestDto;
import com.studentSpringBoot.dto.response.AuthResponseDto;

public interface AuthService {

	AuthResponseDto authenticateUser(AuthRequestDto authRequestDto);

}
