package com.courses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.student.dbutils.DatabaseConnection;

public class CourseDaoImpl implements CourseDao {
	private Connection conn ; 
	public CourseDaoImpl() {
		try {
			conn = DatabaseConnection.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		try {
			if(conn !=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addNewCourse(Course course) throws Exception {
		// TODO Auto-generated method stub
		int rowsAffected = 0;
		String sql = "INSERT  INTO courses(course_code,course_name,department,credits) VALUES(?,?,?,?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, course.getCourseId());
			stmt.setString(2, course.getCourseName());
			stmt.setString(3,course.getDepartment());
			stmt.setInt(4,course.getCredits());
			rowsAffected = stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public int updateCourse(Course updatedCourse, String courseCode) throws Exception {
		int rowsAffected = 0;	
			String sql = "UPDATE TABLE course set course_code=?,course_name=?, department = ?, credits=? WHERE course_code = ?";
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setString(1, updatedCourse.getCourseId());
				stmt.setString(2, updatedCourse.getCourseName());
				stmt.setString(3,updatedCourse.getDepartment());
				stmt.setInt(4, updatedCourse.getCredits());
				stmt.setString(5, courseCode);
				rowsAffected = stmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		return rowsAffected;
	}

	@Override
	public Course findCourseById(String courseCode) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT course_name,course_code,department,credits from courses WHERE course_code = ?";
		Course course = new Course();
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1,courseCode);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				course.setCourseId(rs.getString("course_code"));
				course.setCourseName(rs.getString("course_name"));
				course.setDepartment(rs.getString("department"));
				course.setCredits(rs.getInt("credits"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	@Override
	public int removeCourse(String courseCode) throws Exception {
		int rowsAffected = 0;
		Course course = findCourseById(courseCode);
		
			String sql = "ALTER TABLE courses SET is_active = true WHERE course_code = ?";
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setString(1, courseCode);
				rowsAffected = stmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		return rowsAffected;
	}

	@Override
	public List<Course> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Course> courseList = new ArrayList<>();
		String sql = "SELECT course_code,course_name,department,credits FROM courses WHERE is_active=true";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getString("course_code"));
				course.setCourseName(rs.getString("course_name"));
				course.setDepartment(rs.getString("department"));
				course.setCredits(rs.getInt("credits"));
				courseList.add(course);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}
}
