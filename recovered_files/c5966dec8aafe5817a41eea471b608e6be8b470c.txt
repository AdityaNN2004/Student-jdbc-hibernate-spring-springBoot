package com.studentSpringBoot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentSpringBoot.dto.request.CourseRequestDto;
import com.studentSpringBoot.dto.response.CourseDetailsDto;
import com.studentSpringBoot.dto.response.CourseResponseDto;
import com.studentSpringBoot.entity.Course;
import com.studentSpringBoot.entity.Department;
import com.studentSpringBoot.entity.Teacher;
import com.studentSpringBoot.exceptions.CourseNotFoundException;
import com.studentSpringBoot.exceptions.DepartmentNotFoundException;
import com.studentSpringBoot.exceptions.TeacherProfileMissingException;
import com.studentSpringBoot.repository.CourseRepository;
import com.studentSpringBoot.repository.DepartmentRepository;
import com.studentSpringBoot.repository.TeacherRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	private final CourseRepository courseRepository;
	private final DepartmentRepository departmentRepository;
	private final TeacherRepository teacherRepository;
	
	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public CourseResponseDto addCourse(CourseRequestDto courseDto,Long deptId) {
		Department dept = departmentRepository.findById(deptId)
				.orElseThrow(()-> new DepartmentNotFoundException("Department with ID: "+deptId+" is not foind"));
		
		Course course = new Course();
		course.setCourseCode(courseDto.getCourseCode());
		course.setCourseName(courseDto.getCourseName());
		course.setCredits(courseDto.getCredits());
		course.setDepartment(dept);
		Course savedCourse = courseRepository.save(course);
		CourseResponseDto courseResponseDto = new CourseResponseDto(savedCourse.getId(),
				savedCourse.getCourseCode(), 
				savedCourse.getCourseName(), 
				savedCourse.getCredits(),
				savedCourse.getDepartment().getId(),
				savedCourse.getDepartment().getDepartmentName());

		return courseResponseDto;
		
	}

	@Override
	public List<CourseDetailsDto> getCourseDetailsByDepartment(Long deptId) {
//		Course course = courseRepository.findById(deptId).orElseThrow(()->new CourseNotFoundException("Department with ID: "+deptId+" is not found"));
//		Department dept = departmentRepository.findById(deptId).orElseThrow(()->new DepartmentNotFoundException("Department with ID: "+deptId+" is not found"));
		return courseRepository.findByDepartmentId(deptId)
				.stream()
				.map(course -> new CourseDetailsDto(
							course.getId(), 
							course.getCourseCode(), 
							course.getCourseName(), 
							course.getCredits(), 
							deptId, 
							course.getDepartment().getDepartmentName(), 
							course.getTeacher().getId(), 
							course.getTeacher().getFirstName()+" "+course.getTeacher().getMiddleName()+" "+course.getTeacher().getLastName(), 
							course.getTeacher().getUser().getEmail(), 
							"Course Details fetched Successfully!!")
				)
				.toList();

	}

	@Override
	public CourseDetailsDto assignTeacher(Long courseId, Long teacherId) {
		Course course = courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course with ID: "+courseId+" is not found"));
		Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new TeacherProfileMissingException("Teacher with ID:"+teacherId+ "is not found"));
		course.setTeacher(teacher);
		CourseDetailsDto dto = new CourseDetailsDto(courseId,
				course.getCourseCode(),
				course.getCourseName(), 
				course.getCredits(), 
				course.getDepartment().getId(), 
				course.getDepartment().getDepartmentName(), 
				teacherId, 
				course.getTeacher().getFirstName()+" "+course.getTeacher().getMiddleName()+" "+course.getTeacher().getLastName(), 
				course.getTeacher().getUser().getEmail(), 
				"Assigned Teacher Successfully");
		return dto;
	}

	@Override
	public CourseDetailsDto removeTeacher(Long courseId) {
		Course course = courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course with ID: "+courseId+" is not found"));
		course.setTeacher(null);  //detach entity
		CourseDetailsDto dto = new CourseDetailsDto(courseId,
				course.getCourseCode(),
				course.getCourseName(), 
				course.getCredits(), 
				course.getDepartment().getId(), 
				course.getDepartment().getDepartmentName(), 
				null,
				null,
				null,
				"Removed Teacher Successfully");
		return dto;
	}
}
