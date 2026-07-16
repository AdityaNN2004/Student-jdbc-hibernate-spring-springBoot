package com.studentSpringBoot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentSpringBoot.dto.request.TeacherOnboardRequestDto;
import com.studentSpringBoot.dto.response.TeacherResponse;
import com.studentSpringBoot.service.TeacherService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
	private TeacherService teacherService;
	@GetMapping("/list")
	public ResponseEntity<?> getTeachers(){
		try {
			List<TeacherResponse> teacherResponseDtoList = teacherService.getAllTeacher();
			return ResponseEntity.ok(teacherResponseDtoList);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getTeacherByMobNo(String mobileNo){
		try {
			return ResponseEntity.ok(teacherService.getByMobileNo(mobileNo));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	@PutMapping("/edit-salary/{teacher_id}")
	public ResponseEntity<?> updateSalary(@PathVariable Long id,@RequestBody double salary){
		try {
			return ResponseEntity.ok(teacherService.editSalary(id,salary));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	@GetMapping("/teachersByDept/{deptId}")
	public ResponseEntity<?> getTeachrsByDept(@PathVariable Long deptId){
		try {
			List<TeacherResponse> teacherResponseDtoList = teacherService.getTeacherByDept(deptId);
			return ResponseEntity.ok(teacherResponseDtoList);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
}
