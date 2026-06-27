package com.enrollments;

import java.util.List;
import java.util.Scanner;


public class EnrollmentProgram {
	private static Scanner sc = new Scanner(System.in);
	public static int enrollmentMenuList() {
		int enrollmentChoice;
		System.out.println("-------------------Enrollment Menu---------------------");
		System.out.println("Enter");
		System.out.println("0 for Exit");
		System.out.println("1 for Adding a new Enrollment");
		System.out.println("2 for Get list of all Enrollments");
		System.out.println("3 for Find Course Enrollments by Roll Number");
		System.out.println("Enter your choice: ");
		enrollmentChoice = sc.nextInt();
		System.out.println("-------------------------------------------");
		return enrollmentChoice;
	}
	public static void enrollmentManagement() {
		int enrollmentChoice;
		Enrollment enrollment = new Enrollment();
		try(EnrollmentDaoImpl enrollmentDao = new EnrollmentDaoImpl())
		{
			while((enrollmentChoice = enrollmentMenuList()) !=0) {
				switch(enrollmentChoice) {
				case 1:{
					enrollment.acceptEnrollmentDetails();
					enrollment.displayEnrollmentDetails(enrollment);
					int rowsAffcted = enrollmentDao.addNewEnrollment(enrollment);
					System.out.println("Rows Affcted: "+rowsAffcted);
					break;
					}
				case 2:{
					List<Enrollment> studentList = enrollmentDao.findAll();
					displayEnrollment(studentList);
					break;
					}
				case 3:{
					System.out.println("Enter Roll Number:");
					String EnrollmentCode = sc.next();
					List<Enrollment> enrollmentList = enrollmentDao.findEnrollmentByRollNo(EnrollmentCode);
					displayEnrollment(enrollmentList);
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
	
	private static void displayEnrollment(List<Enrollment> enrollmentList) {
		for(Enrollment enrollment:enrollmentList) {
			System.out.println("Roll Number: "+enrollment.getRollNo());
			System.out.println("Course code:"+enrollment.getCourseCode());
			System.out.println("Semester: "+enrollment.getSemester());
			System.out.println("Grade: "+enrollment.getGrade());
			System.out.println("Enrollment Status: "+enrollment.getEnrollmentStatus());
			System.out.println();
		}
	}
}
