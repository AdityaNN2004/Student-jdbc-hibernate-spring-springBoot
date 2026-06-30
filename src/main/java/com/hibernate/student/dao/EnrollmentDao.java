package com.hibernate.student.dao;

import java.util.List;

import com.hibernate.student.entity.Enrollment;

public interface EnrollmentDao {

	int addNewEnrollment(Enrollment enrollment);

	List<Enrollment> findAll();

	List<Enrollment> findEnrollmentByRollNo(String enrollmentCode);
	
}
	