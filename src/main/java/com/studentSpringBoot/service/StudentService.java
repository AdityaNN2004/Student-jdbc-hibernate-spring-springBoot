package com.studentSpringBoot.service;

import java.util.List;

import com.studentSpringBoot.dto.request.StudentSignUpRequestDto;
import com.studentSpringBoot.dto.request.UpdateStudentContactRequestDto;
import com.studentSpringBoot.dto.response.ApiResponse;
import com.studentSpringBoot.dto.response.StudentResponseDto;
import com.studentSpringBoot.entity.Student;

public interface StudentService {

    List<Student> getAllStudents();

    StudentResponseDto findByRollNumber(String rollNo);

    Student registerStudent(StudentSignUpRequestDto signUpRequestDto, Long deptId);

    ApiResponse editStudentContact(UpdateStudentContactRequestDto updateStudentContactRequestDto);

    ApiResponse deleteStudent(Long id);
}