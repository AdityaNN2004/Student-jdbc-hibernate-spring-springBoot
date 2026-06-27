package com.student;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.student.dbutils.DatabaseConnection;
import com.student.dto.DisplayResultDTO;

class StudentDaoImpl implements StudentDao{
	private Connection conn ;
	public StudentDaoImpl() throws Exception{
		conn = DatabaseConnection.getConnection();
	}
	@Override
	 public int addNewStudent(Student s) {
			int rowAffected=0;
			String sql = "INSERT INTO STUDENTS (roll_no,first_name,last_name,email,date_of_birth,address,gpa,gender,enrollment_date) VALUES(?,?,?,?,?,?,?,?,?)";
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setString(1, s.getRollNo());
				stmt.setString(2, s.getFirstName());
				stmt.setString(3, s.getLastName());
				stmt.setString(4, s.getEmail());
				stmt.setObject(5, s.getDob());
				stmt.setString(6, s.getAddress());
				stmt.setDouble(7, s.getGpa());
				stmt.setString(8, String.valueOf(s.getGender()));
				stmt.setObject(9, s.getEnrollment_date());
				rowAffected = stmt.executeUpdate();
			}
			catch (SQLException e) {
					System.out.println("Database error !!");
					e.printStackTrace();
			}
			return rowAffected;
	}
	@Override
	public void close() throws Exception {
		try {
			if(conn !=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Student> findAll() throws Exception {
		List<Student> studentList = new ArrayList<>();
		String sql = "SELECT roll_no,first_name,last_name,email,date_of_birth,address,gpa,gender,enrollment_date from STUDENTS WHERE is_deleted = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setBoolean(1, false);
			ResultSet rs = stmt.executeQuery();
				while(rs.next()) 
				{
					Student s = new Student();
					s.setRollNo(rs.getString("roll_no"));
					s.setFirstName(rs.getString("first_name"));
					s.setLastName(rs.getString("last_name"));
					s.setEmail(rs.getString("email"));
					s.setDob((LocalDate)rs.getObject("date_of_birth",java.time.LocalDate.class));
					s.setAddress(rs.getString("address"));
					s.setGpa(rs.getDouble("gpa"));
					s.setGender((rs.getString("gender").charAt(0)));
					s.setEnrollment_date((LocalDate)rs.getObject("enrollment_date",java.time.LocalDate.class));
					studentList.add(s);
				}
		}
		catch (SQLException e) {
				System.out.println("Database error !!");
				e.printStackTrace();
		}
		return studentList;
	}
	@Override
	public Student findByRollNo(String rollNo) throws Exception {
		Student s = new Student();
		String sql = "SELECT roll_no,first_name,last_name,email,date_of_birth,address,gpa,gender,enrollment_date FROM STUDENTS WHERE roll_no = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, rollNo);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				s.setRollNo(rs.getString("roll_no"));
				s.setFirstName(rs.getString("first_name"));
				s.setLastName(rs.getString("last_name"));
				s.setEmail(rs.getString("email"));
				s.setDob((LocalDate)rs.getObject("date_of_birth",java.time.LocalDate.class));
				s.setAddress(rs.getString("address"));
				s.setGpa(rs.getDouble("gpa"));
				s.setGender((rs.getString("gender").charAt(0)));
				s.setEnrollment_date((LocalDate)rs.getObject("enrollment_date",java.time.LocalDate.class));
			}
		}
		catch(Exception e) {
			System.out.println("Database Error");
			e.printStackTrace();
		}
		return s;
	}
	@Override
	public int editStudentDetails(String oldRollNo,Student st) throws Exception {
		
		int rowsAffected=0;
		String sql = "Update STUDENTS  set roll_no=?,first_name=?, last_name=?,email=?,date_of_birth=?,address=?,gpa=?,gender=?,enrollment_date=? WHERE roll_no =?";
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setString(1, st.getRollNo());
				stmt.setString(2, st.getFirstName());
				stmt.setString(3, st.getLastName());
				stmt.setString(4, st.getEmail());
				stmt.setObject(5, st.getDob());
				stmt.setString(6, st.getAddress());
				stmt.setDouble(7, st.getGpa());
				stmt.setString(8, String.valueOf(st.getGender()));
				stmt.setObject(9, st.getEnrollment_date());
				stmt.setString(10, oldRollNo);
				rowsAffected = stmt.executeUpdate();
			}catch(Exception e) {
				System.out.println("Database Error");
				e.printStackTrace();
			}
		return rowsAffected;
	}
	@Override
	public int removeStudent(String rollNo) throws Exception {
		int rowsAffected=0;
		
			String sql = "UPDATE STUDENTS SET is_deleted = ? WHERE roll_no = ?";
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setBoolean(1, true);
				stmt.setString(2, rollNo);
				rowsAffected = stmt.executeUpdate();
			}
			catch(Exception e) {
				System.out.println("Database Error");
				e.printStackTrace();
			}
		
		return rowsAffected;
	}
	@Override
	public List<DisplayResultDTO> displayResult() throws Exception {
		String sql = "{call displayResult}";
		List<DisplayResultDTO> dtoList  = new ArrayList<>();
		try(CallableStatement callStmt = conn.prepareCall(sql)){
			try(ResultSet rs = callStmt.executeQuery()){
				while(rs.next()) {
					DisplayResultDTO dto = new DisplayResultDTO(rs.getString("First Name"),rs.getString("Last Name") , rs.getString("Semester"), rs.getString("Course Code"), rs.getString("Course Name"), rs.getInt("Credits"), rs.getString("Grade"), rs.getDouble("GPA"));
					dtoList.add(dto);
				}
			}
		}catch(Exception e) {
			System.err.println("Database Error");
			e.printStackTrace();
		}
		return dtoList;
	}
}