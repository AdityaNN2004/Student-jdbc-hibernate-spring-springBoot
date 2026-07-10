package com.studentSpringBoot.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.studentSpringBoot.dto.request.AuthRequestDto;
import com.studentSpringBoot.dto.request.StudentSignUpRequestDto;
import com.studentSpringBoot.dto.response.AuthResponse;
import com.studentSpringBoot.dto.response.StudentResponseDto;
import com.studentSpringBoot.entity.User;
import com.studentSpringBoot.exceptions.ApiException;
import com.studentSpringBoot.exceptions.UnAuthentivatedUserException;
import com.studentSpringBoot.service.StudentService;
import com.studentSpringBoot.service.TeacherService;
//import com.studentSpringBoot.exceptions.ApiException;
//import com.studentSpringBoot.repository.CourseRepository;
import com.studentSpringBoot.service.UserService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/users")
@RestController
@RestControllerAdvice
@RequiredArgsConstructor
public class UserController 
{
	private final UserService userService;
	@GetMapping("/list")
	public ResponseEntity<?> getUserList(){
		try {
			List<User> userList = userService.getAllUsers();
			if(userList.isEmpty())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			else
				return ResponseEntity.status(HttpStatus.FOUND).body(userList);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiException(e.getMessage()));
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> signIn(@RequestBody AuthRequestDto authRequestDto){
		try {
			AuthResponse authResonseDto = userService.authenticate(authRequestDto);
			if(authResonseDto.isAuthenticated())
				return ResponseEntity.ok(authResonseDto);
			else
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnAuthentivatedUserException("Invalid Email or Password!!"));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiException(e.getMessage()));
		}
	}
	
}
