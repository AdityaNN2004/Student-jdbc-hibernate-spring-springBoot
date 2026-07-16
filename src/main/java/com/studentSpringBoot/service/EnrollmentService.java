package com.studentSpringBoot.service;

import java.util.List;

import com.studentSpringBoot.dto.request.EnrollmentRequestDto;
import com.studentSpringBoot.dto.response.EnrollmentResponseDto;

public interface EnrollmentService {

	EnrollmentResponseDto submitFinalGrade(Long enrollmentId, String grade);

	List<EnrollmentResponseDto> getReportCard(Long studentId);

	EnrollmentResponseDto updateEnrollmentStatus(Long enrollmentId, String status);

//	EnrollmentResponseDto getEnrollments(Long studentId, Long courseId, String semesterCode);

	EnrollmentResponseDto enroll(EnrollmentRequestDto enrollmentRequestDto);

}
