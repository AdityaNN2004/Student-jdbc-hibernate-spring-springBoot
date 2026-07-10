package com.studentSpringBoot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.studentSpringBoot.dto.request.EnrollmentRequestDto;
import com.studentSpringBoot.dto.response.EnrollmentResponseDto;
import com.studentSpringBoot.entity.Enrollment;
import com.studentSpringBoot.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollments")
public class EnrollmentController {
	private final EnrollmentService enrollmentService;
	@GetMapping("/enroll")
	public ResponseEntity<EnrollmentResponseDto> getEnrollStudentInCourse(EnrollmentRequestDto enrollmentRequestDto){
		EnrollmentResponseDto dto = enrollmentService.getEnrollments(enrollmentRequestDto.getStudentId(),enrollmentRequestDto.getCourseId(),enrollmentRequestDto.getSemesterCode());
		return new ResponseEntity<EnrollmentResponseDto>(dto,HttpStatus.OK);
	}
	@PutMapping("/{enrollmentId}/status")
	public ResponseEntity<EnrollmentResponseDto> updateEnrollmentStatus(@PathVariable Long enrollmentId,@RequestParam String status){
		 EnrollmentResponseDto response = enrollmentService.updateEnrollmentStatus(enrollmentId, status);
		  return ResponseEntity.ok(response);
	}
	@PutMapping("/{enrollmentId}/final-grade")
	public ResponseEntity<EnrollmentResponseDto> submitFinalGrade(@PathVariable Long enrollmentId,@RequestParam String grade){
		EnrollmentResponseDto response = enrollmentService.submitFinalGrade(enrollmentId,grade);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{studentId}/report-card")
	public ResponseEntity<List<EnrollmentResponseDto>> getStudentReportCard(@PathVariable Long studentId){
		List<EnrollmentResponseDto> enrollmentsDto = enrollmentService.getReportCard(studentId);
		return ResponseEntity.ok(enrollmentsDto);
	}
}
