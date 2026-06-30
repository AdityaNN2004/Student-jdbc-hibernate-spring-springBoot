package com.hibernate.student.dao;

import java.util.List;

import com.hibernate.student.entity.Course;

public interface CourseDao {

	Course findCourseById(String courseCode);

	int removeCourse(String courseCode);

	int updateCourse(Course c, String courseCode);

	List<Course> findAll();

	int addNewCourse(Course c);

}
	