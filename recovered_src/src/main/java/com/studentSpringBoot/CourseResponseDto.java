package com.studentSpringBoot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDto {
	private Long courseId;
	private String courseCode;
    private String courseName;
    private int credits;
    private Long deptId;
    private String deptName;
}
