package com.student.dbutils;

import java.sql.*;
//import java.time.LocalDate;

//import com.student.Student;

public class DatabaseConnection {
	private static final String DBMANAGER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/students";
	private static final String USERNAME = "D5-92644-Aditya";
	private static final String PASSWORD = "manager";
	static {
		try {
			Class.forName(DBMANAGER);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection () throws Exception{
		Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return con;
	}

}
