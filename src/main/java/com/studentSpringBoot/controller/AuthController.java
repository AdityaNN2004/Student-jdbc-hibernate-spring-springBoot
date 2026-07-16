package com.studentSpringBoot.controller;

//import java.awt.PageAttributes.MediaType;

//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.studentSpringBoot.dto.request.AuthRequestDto;
import com.studentSpringBoot.dto.request.StudentSignUpRequestDto;
import com.studentSpringBoot.dto.request.TeacherOnboardRequestDto;
import com.studentSpringBoot.dto.response.AuthResponseDto;
import com.studentSpringBoot.entity.Student;
import com.studentSpringBoot.entity.User;
import com.studentSpringBoot.entity.UserRole;
import com.studentSpringBoot.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType; 



@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final TeacherService teacherService;
	private final StudentService studentService;
	private final AuthService authService;
	@PostMapping("/teacher/onboard")
	public ResponseEntity<?> onboard(@RequestBody TeacherOnboardRequestDto teacherOnboardRequestDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.onboardTeacher(teacherOnboardRequestDto));
	}
	 @PostMapping(value = "/dept/{deptId}/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)	
	 public ResponseEntity<?> registerStudent(@RequestPart StudentSignUpRequestDto signUpRequestDto,@RequestPart MultipartFile documnetFile,@PathVariable Long deptId){
			Student savedStudent = studentService.registerStudent(signUpRequestDto,documnetFile,deptId);
			return new ResponseEntity<>(savedStudent,HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto){
		AuthResponseDto authResponseDto = authService.authenticateUser(authRequestDto);
		return ResponseEntity.ok(authResponseDto);
	}
}
