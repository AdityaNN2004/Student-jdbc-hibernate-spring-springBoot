package com.studentSpringBoot.controller;

//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentSpringBoot.dto.request.StudentSignUpRequestDto;
import com.studentSpringBoot.dto.request.TeacherOnboardRequestDto;
import com.studentSpringBoot.entity.Student;
import com.studentSpringBoot.service.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final TeacherService teacherService;
	private final StudentService studentService;
	@PostMapping("/teacher/onboard")
	public ResponseEntity<?> onboard(@RequestBody TeacherOnboardRequestDto teacherOnboardRequestDto){
		return ResponseEntity.ok(teacherService.onboardTeacher(teacherOnboardRequestDto));
	}
	@PostMapping("/dept/{deptId}/register")
	public ResponseEntity<?> addNewStudent(@RequestBody StudentSignUpRequestDto signUpRequestDto,@PathVariable Long deptId){
			Student savedStudent = studentService.registerStudent(signUpRequestDto,deptId);
			return ResponseEntity.ok(savedStudent);
	}
}
