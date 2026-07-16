package com.studentSpringBoot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentSpringBoot.dto.response.DepartmentResponseDto;
import com.studentSpringBoot.entity.Department;
import com.studentSpringBoot.exceptions.*;
import com.studentSpringBoot.service.DepartmentService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/dept")
@RestController
@RequiredArgsConstructor
public class DepartmentController {
	private final DepartmentService deptService;
	@GetMapping
	public ResponseEntity<?> getDepartmentList(){
		List<DepartmentResponseDto> dtoList = deptService.getAllDepartments();
		if(dtoList.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		else
			return ResponseEntity.ok(dtoList);
	}
	@PostMapping("/add-dept")
	public ResponseEntity<?> addNewDepartment(@RequestBody Department dept){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(deptService.addDept(dept));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(e.getMessage()));
		}
	}
	@GetMapping("/details")
	public ResponseEntity<?> getDepartmentDetails(String deptName){
		Department deptDetails = deptService.getDeptDetails(deptName);
		if(deptDetails == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResourceNotFoundException("No Department found!!"));
		}else {
			return ResponseEntity.ok(deptDetails);
		}
	}
	@PutMapping("/edit-hod")
	public ResponseEntity<?> updateHod(@PathVariable Long id,@RequestBody String hodName){
		Department dept = deptService.editHod(id,hodName);
		return ResponseEntity.ok(dept);
	}
}
