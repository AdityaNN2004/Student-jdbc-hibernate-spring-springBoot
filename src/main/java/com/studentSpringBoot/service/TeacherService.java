package com.studentSpringBoot.service;

import java.util.List;

//import org.jspecify.annotations.Nullable;

import com.studentSpringBoot.dto.request.TeacherOnboardRequestDto;
import com.studentSpringBoot.dto.response.ApiResponse;

//import org.jspecify.annotations.Nullable;

import com.studentSpringBoot.dto.response.TeacherResponse;

public interface TeacherService {

	TeacherResponse getByMobileNo(String mobileNo);

	List<TeacherResponse> getAllTeacher();

	ApiResponse editSalary(Long id, double salary);

	List<TeacherResponse> getTeacherByDept(Long deptId);

	TeacherResponse onboardTeacher(TeacherOnboardRequestDto teacherOnboardRequestDto);

}
