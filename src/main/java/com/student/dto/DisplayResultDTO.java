package com.student.dto;

public class DisplayResultDTO {
	private String firstName;
	private String lastName;
	private String semester;
	private String courseCode;
	private String courseName;
	private int credits;
	private String grade;
	private double gpa;
	public DisplayResultDTO() {
		
	}
	public DisplayResultDTO(String firstName, String lastName, String semester, String courseCode, String courseName,
			int credits, String grade, double gpa) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.semester = semester;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.credits = credits;
		this.grade = grade;
		this.gpa = gpa;
	}
	@Override
	public String toString() {
		return "DisplayResultDTO [firstName=" + firstName + ", lastName=" + lastName + ", semester=" + semester
				+ ", courseCode=" + courseCode + ", courseName=" + courseName + ", credits=" + credits + ", grade="
				+ grade + ", gpa=" + gpa + "]";
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
}
