package com.hibernate.student.dao;

import java.util.List;

import com.hibernate.student.entity.Student;

public interface StudentDao {
	
	List<Student> getStudentList() throws Exception;

	Student findStudentByRollNo(String rollNo);

	int removeStudent(String rollNo);

	int editStudentDetails(Student s, String rollNo);

	int addNewStudent(Student stud);

	
}
