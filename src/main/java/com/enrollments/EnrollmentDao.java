package com.enrollments;

import java.util.List;

public interface EnrollmentDao extends AutoCloseable {
	List<Enrollment> findAll() throws Exception;
	int addNewEnrollment(Enrollment enrollment) throws Exception;
	int updateEnrollment(Enrollment oldEnrollment,String rollNo) throws Exception;
	int deleteEnrollment(String rollNo) throws Exception;
	List<Enrollment> findEnrollmentByRollNo(String rollNo) throws Exception;
}
