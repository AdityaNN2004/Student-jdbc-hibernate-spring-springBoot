package com.courses;

import java.util.List;

public interface CourseDao extends AutoCloseable{
	int addNewCourse(Course course) throws Exception;
	int updateCourse(Course course,String courseCode) throws Exception;
	Course findCourseById(String courseCode) throws Exception;
	int removeCourse(String courseCode) throws Exception;
	List<Course> findAll() throws Exception;
}
