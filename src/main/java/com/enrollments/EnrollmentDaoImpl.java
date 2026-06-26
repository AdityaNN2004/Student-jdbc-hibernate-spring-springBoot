package com.enrollments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.student.dbutils.DatabaseConnection;

public class EnrollmentDaoImpl implements EnrollmentDao {
	private Connection conn;
	public EnrollmentDaoImpl() {
		try {
			conn = DatabaseConnection.getConnection();
		} catch (Exception e) {
			System.err.println("Error while getting DB Connection");
			e.printStackTrace();
		}
	}
	@Override
	public void close() throws Exception {
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(Exception e) {
			System.err.println("Error while closing DB Connection Error");
			e.printStackTrace();
		}

	}

	@Override
	public List<Enrollment> findAll() throws Exception {
		String sql = "select roll_no,course_code,semester,grade,enrollment_status from enrollments where is_deleted = ?";
		List<Enrollment> enrollmentList = new ArrayList<>();
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setBoolean(1, false);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Enrollment enrollment = new Enrollment();
				enrollment.setRollNo(rs.getString("roll_no"));
				enrollment.setGrade(rs.getString("course_code"));
				enrollment.setSemester(rs.getString("semester"));
				enrollment.setGrade(rs.getString("grade"));
				enrollment.setEnrollmentStatus(rs.getString("enrollment_status"));
				enrollmentList.add(enrollment);
			}
		}catch(Exception e) {
			System.err.println("Database Error");
			e.printStackTrace();
		}
		return enrollmentList;
	}

	@Override
	public int addNewEnrollment(Enrollment enrollment) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into enrollments(roll_no,course_code,semester,grade,enrollment_status) values (?,?,?,?,?)";
		int rowsAffected=0;
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setString(1,enrollment.getRollNo());
				stmt.setString(2,enrollment.getCourseCode());
				stmt.setString(3,enrollment.getSemester());
				stmt.setString(4,enrollment.getGrade());
				stmt.setString(5,enrollment.getEnrollmentStatus());
				rowsAffected =stmt.executeUpdate();
		}catch(Exception e) {
			System.err.println("Database Error");
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public int updateEnrollment(Enrollment newEnrollment, String rollNo) throws Exception {
		int rowsAffected = 0;
			String sql ="Update enrollments set roll_no = ?,course_coode = ?,semester=?,grade= ?,enrollment_status=? where roll_no= ?";
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setString(1, newEnrollment.getRollNo());
				stmt.setString(2, newEnrollment.getCourseCode());
				stmt.setString(3, newEnrollment.getSemester());
				stmt.setString(4, newEnrollment.getGrade());
				stmt.setString(5, newEnrollment.getEnrollmentStatus());
				stmt.setString(6, rollNo);
				rowsAffected = stmt.executeUpdate();
			}catch(Exception e) {
				System.err.println("Database Error");
				e.printStackTrace();
			}
		return rowsAffected;
	}

	@Override
	public int deleteEnrollment(String rollNo) throws Exception {
		// TODO Auto-generated method stub
		int rowsAffected = 0;
			String sql ="Update enrollments set is_deleted = ? where roll_no= ?";
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setBoolean(1, true);
				stmt.setString(2, rollNo);
				rowsAffected = stmt.executeUpdate();
			}catch(Exception e) {
				System.err.println("Database Error");
				e.printStackTrace();
			}
		return rowsAffected;
	}

	@Override
	public List<Enrollment> findEnrollmentByRollNo(String rollNo) throws Exception {
		// TODO Auto-generated method 
		List<Enrollment> enrollmentList = new ArrayList<>(); 
		String sql = "select roll_no,course_code,semester,grade,enrollment_status from enrollments where roll_no = ? and is_deleted=?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, rollNo);
			stmt.setBoolean(2, false);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Enrollment enrollment = new Enrollment();
				enrollment.setRollNo(rs.getString("roll_no"));
				enrollment.setCourseCode(rs.getString("course_code"));
				enrollment.setSemester(rs.getString("semester"));
				enrollment.setGrade(rs.getString("grade"));
				enrollment.setEnrollmentStatus(rs.getString("enrollment_status"));
				enrollmentList.add(enrollment);
			}
		}catch(Exception e) {
			System.err.println("Database Error");
			e.printStackTrace();
		}
		return enrollmentList;
	}

}
