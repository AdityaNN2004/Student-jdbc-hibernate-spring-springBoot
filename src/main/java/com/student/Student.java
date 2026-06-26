package com.student;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class Student {
	
//	private List<Student> studentList=  new ArrayList<>();
	
//	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "D5-92644-Aditya", "manager");
	public Student() {
		
	}
	private String rollNo;
	private String firstName;
	public Student(String rollNo, String firstName, String lastName, String email, LocalDate dob, String address,
			double gpa, char gender, LocalDate enrollment_date) {
		super();
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.address = address;
		this.gpa = gpa;
		this.gender = gender;
//		this.studentId = studentId;
		this.enrollment_date = enrollment_date;
	}
	private String lastName;
	private String email;
	private LocalDate dob;
	private String address;
	private double gpa;
	private Character gender;	
	private int studentId;
	private LocalDate enrollment_date;
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public LocalDate getEnrollment_date() {
		return enrollment_date;
	}
	public void setEnrollment_date(LocalDate enrollment_date) {
		this.enrollment_date = enrollment_date;
	}
	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", dob=" + dob + ", address=" + address + ", gpa=" + gpa + ", gender=" + gender + ", studentId="
				+ studentId + ", enrollment_date=" + enrollment_date + "]";
	}
	public void acceptStudentDetails() {
//		Student s = new Student();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Student details");
		System.out.println("Enter Roll Number: ");
		String rollNo = sc.nextLine();
		this.setRollNo(rollNo);
		System.out.println("Enter First Name: ");
		String fName = sc.nextLine();
		this.setFirstName(fName);
		System.out.println("Enter Last Name:");
		String lName =sc.nextLine();
		this.setLastName(lName);
		System.out.println("Enter Email:");
		String email = sc.nextLine();
		this.setEmail(email);
		System.out.println("Enter Date of Birth in format YYYY-MM-DD:");
		String dateOfBirth = sc.nextLine();
		LocalDate dob = LocalDate.parse(dateOfBirth);
		this.setDob(dob);
		System.out.println("Enter Address");
		String addr = sc.nextLine();
		this.setAddress(addr);
		System.out.println("Enter GPA:");
		double gpa =sc.nextDouble();
		this.setGpa(gpa);
		System.out.println("Enter Gender-M for Male and F for Female:");
		char gender = sc.next().charAt(0);
		sc.nextLine();
		this.setGender(gender);
		System.out.println("Enter Enrollment Date in format YYYY-MM-DD:");
		String enroll_date= sc.nextLine();
		LocalDate enrollmentDate = LocalDate.parse(enroll_date);
		this.setEnrollment_date(enrollmentDate);
	
	}
	public void displayStudentDetails(Student s) {
		System.out.println("------------Student Details----------------");
		System.out.println("Roll Number: "+s.getRollNo());
		System.out.println("First Name:"+s.getFirstName());
		System.out.println("Last Name: "+s.getLastName());
		System.out.println("Email: "+s.getEmail());
		System.out.println("Date Of Birth: "+s.getDob());
		System.out.println("Address:"+s.getAddress());
		System.out.println("Gender: "+s.getGender());
		System.out.println("Enrollement Date: "+s.getEnrollment_date());
		System.out.println("-------------------------------------------");
	}
}
