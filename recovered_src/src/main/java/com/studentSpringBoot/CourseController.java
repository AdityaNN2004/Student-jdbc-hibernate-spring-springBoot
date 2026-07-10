package com.studentSpringBoot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.studentSpringBoot.dto.request.CourseRequestDto;
import com.studentSpringBoot.dto.response.CourseDetailsDto;
import com.studentSpringBoot.dto.response.CourseResponseDto;
import com.studentSpringBoot.entity.Course;
import com.studentSpringBoot.exceptions.ApiException;
import com.studentSpringBoot.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
	private final CourseService courseService;
	@GetMapping("/list")
	public ResponseEntity<?> getCourseList(){
		List<Course> courseList = courseService.getAllCourses();
		try {
		if(courseList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.ok(courseList);
		}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiException(e.getMessage()));
		}
	}
	@PostMapping("/dept/{deptId}/add-course")
	public ResponseEntity<?> addNewCourse(@RequestBody CourseRequestDto courseDto,@PathVariable Long deptId){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courseDto,deptId));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiException(e.getMessage()));
		}
	}
	@GetMapping("/course-by-deptName/{deptId}")
	public ResponseEntity<List<CourseDetailsDto>> getCourseByDeptName(@PathVariable Long deptId){
		List<CourseDetailsDto> courseDetailsDto = courseService.getCourseDetailsByDepartment(deptId);
		return new ResponseEntity<>(courseDetailsDto,HttpStatus.FOUND);
	}
	
	@PutMapping("/{courseId}/assign-teacher/{teacherId}")
	public ResponseEntity<CourseDetailsDto> assignATeacher(@PathVariable Long courseId,@PathVariable Long teacherId){
		CourseDetailsDto courseDetailsDto = courseService.assignTeacher(courseId,teacherId);
		return new ResponseEntity<>(courseDetailsDto,HttpStatus.OK);
	}
	
	@PutMapping("/{courseId}/remove-teacher")
	public ResponseEntity<?> removeTeacher(@PathVariable Long courseId){
		CourseDetailsDto dto = courseService.removeTeacher(courseId);
		return new ResponseEntity<>(dto,HttpStatus.OK);	
	}
}
