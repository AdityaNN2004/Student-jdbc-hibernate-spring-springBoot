package com.studentSpringBoot.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.studentSpringBoot.dto.request.AuthRequestDto;
import com.studentSpringBoot.dto.response.AuthResponseDto;
import com.studentSpringBoot.entity.User;
import com.studentSpringBoot.repository.UserRepository;
import com.studentSpringBoot.security.JwtUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
	@Override
	public AuthResponseDto authenticateUser(AuthRequestDto authRequestDto) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(authRequestDto.getEmail()).orElseThrow(()->new BadCredentialsException("Invalid email or password credentials provided."));
		if(!passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
			throw new BadCredentialsException("Invalid email or password credentials provided.");
		}
		String generatedToken = jwtUtils.generateJwt(user.getEmail());
		return new AuthResponseDto(generatedToken,user.getEmail(),user.getUserRole());
	}

}
