package com.studentSpringBoot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnrollmentRequestDto {
	 private Long studentId;
	 private Long courseId;
	 private String semesterCode;
}
