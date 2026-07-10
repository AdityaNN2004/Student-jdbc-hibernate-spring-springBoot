package com.studentSpringBoot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailsDto {
	private Long courseId;
    private String courseCode;
    private String courseName;
    private int credits;
    private Long deptId;
    private String deptName;
    private Long teacherId;
    private String teacherFullName;
    private String teacherEmail;
    private String message;
}
