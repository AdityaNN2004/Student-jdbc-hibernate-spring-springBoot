package com.enrollments;

import java.util.Scanner;

public class Enrollment {
	private String rollNo;
	private String courseCode;
	private String semester;
	private String grade;
	private String enrollmentStatus;
	public Enrollment() {
		// TODO Auto-generated method stub
	}
	public Enrollment(String rollNo, String courseCode, String semester, String enrollmentStatus) {
		this.rollNo = rollNo;
		this.courseCode = courseCode;
		this.semester = semester;
		this.enrollmentStatus = enrollmentStatus;
	}
	@Override
	public String toString() {
		return "Enrollment [rollNo=" + rollNo + ", courseCode=" + courseCode + ", semester=" + semester
				+ ", enrollmentStatus=" + enrollmentStatus + "]";
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}
	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
	public void acceptEnrollmentDetails() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------Enter Course Details-------------------");
		System.out.println("Enter Roll Number: ");
		String rollNo = sc.nextLine();
		this.setCourseCode(rollNo);
		System.out.println("Enter Course Code: ");
		String courseCode = sc.nextLine();
		this.setCourseCode(courseCode);
		System.out.println("Enter Semester: ");
		String semester = sc.nextLine();
		this.setSemester(semester);
		System.out.println("Enter Grade:");
		String grade =sc.nextLine();
		this.setGrade(grade);
		System.out.println("Enter Enrollment status: ");
		String enrollment = sc.nextLine();
		this.setEnrollmentStatus(enrollment);
		System.out.println("------------------------------------------------------");
		
	}
	public void displayEnrollmentDetails(Enrollment enrollment) {
		// TODO Auto-generated method stub
		System.out.println("Roll Number: "+enrollment.getRollNo());
		System.out.println("Course code:"+enrollment.getCourseCode());
		System.out.println("Semester: "+enrollment.getSemester());
		System.out.println("Grade: "+enrollment.getGrade());
		System.out.println("Enrollment Status: "+enrollment.getEnrollmentStatus());
		System.out.println();
	}
	
}
