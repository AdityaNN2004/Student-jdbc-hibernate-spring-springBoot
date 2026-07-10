package com.studentSpringBoot.service;

import java.util.List;
//import java.util.Optional;
//import java.util.function.Supplier;
import java.util.Optional;

//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studentSpringBoot.dto.request.AuthRequestDto;
import com.studentSpringBoot.dto.response.AuthResponse;
import com.studentSpringBoot.entity.User;
//import com.studentSpringBoot.exceptions.UserNotFoundException;
import com.studentSpringBoot.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User signUp(User user) {
		// TODO Auto-generated method stub
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public AuthResponse authenticate(AuthRequestDto authRequestDto) {
		Optional<User> user = userRepository.findByEmail(authRequestDto.getEmail());
		AuthResponse authResponse = new AuthResponse();
		if(!user.isEmpty()) {
			authResponse.setId(user.get().getId());
			authResponse.setEmail(user.get().getEmail());
			authResponse.setUserRole(user.get().getUserRole());
			authResponse.setAuthenticated(true);
		}
		return authResponse;
	}
	
}
