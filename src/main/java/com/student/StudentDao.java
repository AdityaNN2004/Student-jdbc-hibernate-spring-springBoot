package com.student;

import java.util.List;

import com.student.dto.DisplayResultDTO;

public interface StudentDao extends AutoCloseable {
	int addNewStudent(Student s) throws Exception;
	List<Student> findAll() throws Exception;
	Student findByRollNo(String roll) throws Exception;
	int editStudentDetails(String rollNO,Student s) throws Exception;
	int removeStudent(String rollNo) throws Exception;
	List<DisplayResultDTO> displayResult() throws Exception;
}
