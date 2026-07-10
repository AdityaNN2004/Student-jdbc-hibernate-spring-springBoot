package com.studentSpringBoot.controller;

import java.util.List;

//import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.studentSpringBoot.dto.request.StudentSignUpRequestDto;
import com.studentSpringBoot.dto.request.UpdateStudentContactRequestDto;
//import com.studentSpringBoot.dto.response.ApiResponse;
import com.studentSpringBoot.dto.response.StudentResponseDto;
import com.studentSpringBoot.entity.Student;
//import com.studentSpringBoot.exceptions.ApiException;
//import com.studentSpringBoot.exceptions.ErrorResponse;
//import com.studentSpringBoot.exceptions.GlobalExceptionHandler;
//import com.studentSpringBoot.exceptions.UserNotFoundException;
import com.studentSpringBoot.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

//    private final UserController userController;
	private final StudentService studentService;


	@GetMapping("/list")
	public ResponseEntity<?> getStudentList(){
		List<Student> studentList = studentService.getAllStudents(); // all active students
		try {
		if(studentList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(studentList);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	@GetMapping("/search-student")
	public ResponseEntity<?> findStudentByRollNo(@RequestParam String rollNo){
		try {
			StudentResponseDto studentResponseDto = studentService.findByRollNumber(rollNo);
			return ResponseEntity.status(HttpStatus.FOUND).body(studentResponseDto);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	@PutMapping("/edit-contact")
	public ResponseEntity<?> updateStudentContact(@RequestBody UpdateStudentContactRequestDto updateStudentContactRequestDto){
		try {
			return ResponseEntity.ok(studentService.editStudentContact(updateStudentContactRequestDto));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	@DeleteMapping("/remove")
	public ResponseEntity<?> removeStudent(@PathVariable Long id){
		try {
			return ResponseEntity.ok(studentService.deleteStudent(id));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
}
