package com.courses;

import java.util.List;
import java.util.Scanner;

import com.courses.CourseDaoImpl;

public class Program {
	private static Scanner sc = new Scanner(System.in);
	public static int menuList() {
		int choice;
		System.out.println("-------------------Menu---------------------");
		System.out.println("Enter");
		System.out.println("0 for Exit");
		System.out.println("1 for Adding a new Course");
		System.out.println("2 for Get list of Courses");
		System.out.println("3 for Find a Cource by Cource Code/ID");
		System.out.println("4 for Updating Course details");
		System.out.println("5 for Deleting a Course");
		System.out.println("Enter your choice: ");
		choice = sc.nextInt();
		System.out.println("-------------------------------------------");
		return choice;
	}
	public static void main(String[] args) {
		int choice;
		Course c = new Course();
		try(CourseDaoImpl courseDao = new CourseDaoImpl())
		{
			while((choice = menuList()) !=0) {
				switch(choice) {
				case 1:{
					c.acceptCourseDetails();
					c.displayCourseDetails(c);
					int rowsAffcted = courseDao.addNewCourse(c);
					System.out.println("Rows Affcted: "+rowsAffcted);
					break;
					}
				case 2:{
					List<Course> studentList = courseDao.findAll();
					displayCourse(studentList);
					break;
					}
				case 3:{
					System.out.println("Enter Course Code:");
					String courseCode = sc.next();
					Course c1 = courseDao.findCourseById(courseCode);
					c1.displayCourseDetails(c1);
					break;
					}
				case 4:{
					System.out.println("Enter course code:");
					String courseCode = sc.next();
					Course oldCourse = courseDao.findCourseById(courseCode);
					if(oldCourse !=null) {
					c.acceptCourseDetails();
					int rowsAffected = courseDao.updateCourse(c,courseCode);
						System.out.println("Rows Affected: "+rowsAffected);
						System.out.println("Student with roll number: "+courseCode+" is updated successfully");
					}
					else {
						System.out.println("Student with roll number: "+courseCode+" is not found");
					}
					break;
				}
				case 5:{
					System.out.println("Enter course code:");
					String courseCode = sc.next();
					Course st = courseDao.findCourseById(courseCode);
					if(st !=null) {
						int rowsAffected = courseDao.removeCourse(courseCode);
						System.out.println("Rows Affected: "+rowsAffected);
						System.out.println("Student with roll number: "+courseCode+" is deleted");
					}
					else {
						System.out.println("Student with roll number: "+courseCode+" is not found");
					}
					break;
					}
				
				default:
					System.out.println("Invalid Choice");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private static void displayCourse(List<Course> courseList) {
		for(Course c:courseList) {
			System.out.println("Course Code: "+c.getCourseId());
			System.out.println("Course Name:"+c.getCourseName());
			System.out.println("Department: "+c.getDepartment());
			System.out.println("Credits: "+c.getCredits());
			System.out.println();
		}
	}
}
