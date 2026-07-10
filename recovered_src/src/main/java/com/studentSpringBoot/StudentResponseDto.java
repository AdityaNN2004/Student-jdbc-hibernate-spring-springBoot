package com.studentSpringBoot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
	private Long studentId;
	private String fullName;
	private String email;
	private String rollNo;
	private Long deptId;
	private String deptName;
}
