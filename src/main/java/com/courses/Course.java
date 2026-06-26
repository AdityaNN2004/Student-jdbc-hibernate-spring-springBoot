package com.courses;

import java.util.Scanner;

public class Course {
	private String courseId;
	private String courseName;
	private String department;
	private int credits;
	private boolean isActive;
	public Course() {
		
	}
	public Course(String courseId, String courseName, String department, int credits, boolean isActive) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.department = department;
		this.credits = credits;
		this.isActive = isActive;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", department=" + department
				+ ", credits=" + credits + ", isActive=" + isActive + "]";
	}
	
	public void acceptCourseDetails() {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------Enter Course Details-------------------");
		System.out.println("Enter Course Code: ");
		String courseId = sc.nextLine();
		this.setCourseId(courseId);
		System.out.println("Enter Course Name: ");
		String courseName = sc.nextLine();
		this.setCourseName(courseName);
		System.out.println("Enter Department Name:");
		String department =sc.nextLine();
		this.setDepartment(department);
		System.out.println("Enter Credits: ");
		credits = sc.nextInt();
		this.setCredits(credits);
		System.out.println("------------------------------------------------------");
	}
	public void displayCourseDetails(Course c) {
		System.out.println("---------------Course Details-------------------");
		System.out.println("Course Code: "+c.getCourseId());
		System.out.println("Course Name:"+c.getCourseName());
		System.out.println("Department: "+c.getDepartment());
		System.out.println("Credits: "+c.getCredits());
		System.out.println();
		System.out.println("------------------------------------------------");
	}
	
}
