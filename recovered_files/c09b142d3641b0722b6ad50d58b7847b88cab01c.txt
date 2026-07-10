package com.studentSpringBoot.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherResponse {
	private Long userId;
	private Long deptId;
	private String fullName;
	private String email;
	private LocalDate doj;
	private double salary;
	private String deptName;
}
