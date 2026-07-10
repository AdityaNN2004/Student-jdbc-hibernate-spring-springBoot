package com.studentSpringBoot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter@AllArgsConstructor@NoArgsConstructor
public class CourseRequestDto {
	private String courseCode;
    private String courseName;
    private int credits;
}
