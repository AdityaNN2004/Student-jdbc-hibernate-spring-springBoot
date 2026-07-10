package com.studentSpringBoot.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.studentSpringBoot.dto.request.CourseRequestDto;
import com.studentSpringBoot.dto.response.CourseDetailsDto;
import com.studentSpringBoot.dto.response.CourseResponseDto;
import com.studentSpringBoot.entity.Course;

public interface CourseService {

	List<Course> getAllCourses();

	List<CourseDetailsDto> getCourseDetailsByDepartment(Long deptId);

	CourseResponseDto addCourse(CourseRequestDto courseDto, Long deptId);

	CourseDetailsDto assignTeacher(Long courseId, Long teacherId);

	CourseDetailsDto removeTeacher(Long courseId);

}
