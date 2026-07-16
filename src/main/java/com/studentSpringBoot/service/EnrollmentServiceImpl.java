package com.studentSpringBoot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentSpringBoot.dto.request.EnrollmentRequestDto;
import com.studentSpringBoot.dto.response.EnrollmentResponseDto;
import com.studentSpringBoot.entity.Course;
import com.studentSpringBoot.entity.Enrollment;
import com.studentSpringBoot.entity.EnrollmentStatus;
import com.studentSpringBoot.entity.Student;
import com.studentSpringBoot.exceptions.CourseNotFoundException;
import com.studentSpringBoot.exceptions.EnrollmentNotFoundException;
import com.studentSpringBoot.exceptions.StudentProfileMissingException;
import com.studentSpringBoot.repository.CourseRepository;
import com.studentSpringBoot.repository.EnrollmentRepository;
import com.studentSpringBoot.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
	private final EnrollmentRepository enrollmentRepository;
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
	@Override
	public EnrollmentResponseDto enroll(EnrollmentRequestDto enrollmentRequestDto) {
		log.info("Processing enrollment request for Student ID: {} into Course ID: {} for Semester: {}", 
				enrollmentRequestDto.getStudentId(), enrollmentRequestDto.getCourseId(), enrollmentRequestDto.getSemesterCode());
		Student student = studentRepository.findById(enrollmentRequestDto.getStudentId()).orElseThrow(()-> new StudentProfileMissingException("Student with ID: "+enrollmentRequestDto.getStudentId()+ " is not found"));
		Course course = courseRepository.findById(enrollmentRequestDto.getCourseId()).orElseThrow(()-> new CourseNotFoundException("Course with ID: "+enrollmentRequestDto.getCourseId()+" is not found"));
		Enrollment enrollment = new Enrollment();
		enrollment.setSemesterCode(enrollmentRequestDto.getSemesterCode());;
		enrollment.setGpa(0.0);
		enrollment.setEnrollmentStatus(EnrollmentStatus.ACTIVE);
		enrollment.setCourse(course);
		enrollment.setStudent(student);
		enrollment.setDepartment(course.getDepartment());	
		Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
		log.info("Successfully committed Enrollment Ledger Entry. Generated Record ID: [{}]", savedEnrollment.getId());
		return mapToResponseDto(savedEnrollment,null);
	}
	@Override
	public EnrollmentResponseDto updateEnrollmentStatus(Long enrollmentId, String status) {
		Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(()->new EnrollmentNotFoundException("Enrollment with ID: "+enrollmentId+" is not found"));
		try {
		enrollment.setEnrollmentStatus(EnrollmentStatus.valueOf(status.toUpperCase().trim()));
	 	} catch (IllegalArgumentException e) {
	        throw new RuntimeException("Invalid enrollment status value provided: " + status);
	    }
		return mapToResponseDto(enrollment,null);
	}
	
	
	@Override
	public EnrollmentResponseDto submitFinalGrade(Long enrollmentId, String grade) {
		double gpaCalculated = calculateGpaWeight(grade);
		Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(()->new EnrollmentNotFoundException("Enrollment with ID: "+enrollmentId+" is not found"));
		enrollment.setGpa(gpaCalculated);
		return mapToResponseDto(enrollment, grade);
	}

	private double calculateGpaWeight(String grade) {
	    return switch (grade.toUpperCase().trim()) {
	        case "A+" -> 4.0;
	        case "A"  -> 4.0;
	        case "B+" -> 3.5;
	        case "B"  -> 3.0;
	        case "C+" -> 2.5;
	        case "C"  -> 2.0;
	        case "D"  -> 1.0;
	        default   -> 0.0; 
	    };
	}
	private EnrollmentResponseDto mapToResponseDto(Enrollment enrollment,String grade) {
		EnrollmentResponseDto dto = new EnrollmentResponseDto(
				enrollment.getId(),
				enrollment.getStudent().getId(),
				enrollment.getStudent().getFirstName()+" "+enrollment.getStudent().getMiddleName()+" "+enrollment.getStudent().getLastName(),
				enrollment.getCourse().getId(),
				enrollment.getCourse().getCourseCode(),
				enrollment.getCourse().getCourseName(),
				enrollment.getDepartment().getId(),
				enrollment.getDepartment().getDepartmentName(),
				enrollment.getSemesterCode(),
				enrollment.getGpa(),
				enrollment.getEnrollmentStatus(),
				grade
			);
		return dto;
	}
	@Override
	public List<EnrollmentResponseDto> getReportCard(Long studentId) {
		return enrollmentRepository.findByStudentId(studentId).
				stream()
				.map(enrollment -> mapToResponseDto(enrollment, null))
				.toList();
	}
	
	
}
