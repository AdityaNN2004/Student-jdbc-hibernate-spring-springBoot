package com.studentSpringBoot.dto.response;

import com.studentSpringBoot.entity.EnrollmentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseDto {
	private Long enrollmentId;
    private Long studentId;
    private String studentFullName;

    private Long courseId;
    private String courseName;
    private String courseCode;

    private Long deptId;
    private String deptName;

    private String semesterCode;
    private double gpa;          
    private EnrollmentStatus status; 
    private String grade;
}
