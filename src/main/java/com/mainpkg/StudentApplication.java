package com.mainpkg;

import java.util.Scanner;

import com.courses.CourseProgram;
import com.enrollments.EnrollmentProgram;
import com.student.StudentProgram;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class StudentApplication {
	private static Scanner sc = new Scanner(System.in);
	public static int menuList() {
		int ch =0;
		System.out.println("================ MASTER MANAGEMENT SYSTEM ================");
		System.out.println("0. Exit Application");
		System.out.println("1. Manage Students");
		System.out.println("2. Manage Courses");
		System.out.println("3. Manage Enrollments");
		System.out.println("Enter your choice: ");
		ch = sc.nextInt();
		System.out.println("==========================================================");
		return ch;
	}
	public static void main(String[] args) {
		int ch = 0;
		try {
			while((ch = menuList())!=0) {
				switch(ch) {
				case 1:{
					StudentProgram.studentManagement();
					break;
				}
				case 2:{
					CourseProgram.courseManagement();
					break;
				}
				case 3:{
					EnrollmentProgram.enrollmentManagement();
					break;
				}
				default:
					System.err.println("Invalid Case");
				}
			}
		}catch(Throwable th) {
			System.err.println("Error or Exception");
			th.printStackTrace();
		}
	}

}
