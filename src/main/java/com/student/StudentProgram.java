package com.student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.student.dto.DisplayResultDTO;

@SuppressWarnings("unused")
public class StudentProgram {
	private static Scanner sc = new Scanner(System.in);
	public static int studentMenuList() {
		int studentChoice;
		System.out.println("-------------------Student Menu---------------------");
		System.out.println("Enter");
		System.out.println("0 for Exit");
		System.out.println("1 for Adding a new Student");
		System.out.println("2 for Get list of Students");
		System.out.println("3 for Find a Student by Roll Number");
		System.out.println("4 for Updating Student details");
		System.out.println("5 for Deleting a Student");
		System.out.println("6.for Display Result Board");
		System.out.println("Enter your choice: ");
		studentChoice = sc.nextInt();
		System.out.println("-------------------------------------------");
		return studentChoice;
	}
	public static void studentManagement() {
		int studentChoice;
		Student s = new Student();
		try(StudentDaoImpl studentDao = new StudentDaoImpl())
		{
			while((studentChoice = studentMenuList()) !=0) {
				switch(studentChoice) {
				case 1:{
					s.acceptStudentDetails();
					s.displayStudentDetails(s);
					int rowsAffcted = studentDao.addNewStudent(s);
					System.out.println("Rows Affcted: "+rowsAffcted);
					break;
					}
				case 2:{
					List<Student> studentList = studentDao.findAll();
					displayStudent(studentList);
					break;
					}
				case 3:{
					System.out.println("Enter roll number:");
					String rollNo = sc.next();
					Student s1 = studentDao.findByRollNo(rollNo);
					s1.displayStudentDetails(s1);
					break;
					}
				case 4:{
					System.out.println("Enter roll number:");
					String rollNo2 = sc.next();
					Student st = studentDao.findByRollNo(rollNo2);
					if(st !=null) {
					s.acceptStudentDetails();
					int rowsAffected = studentDao.editStudentDetails(rollNo2,s);
						System.out.println("Rows Affected: "+rowsAffected);
						System.out.println("Student with roll number: "+rollNo2+" is updated successfully");
					}
					else {
						System.out.println("Student with roll number: "+rollNo2+" is not found");
					}
					break;
				}
				case 5:{
					System.out.println("Enter roll number:");
					String rollNo1 = sc.next();
					Student st = studentDao.findByRollNo(rollNo1);
					if(st !=null) {
						int rowsAffected = studentDao.removeStudent(rollNo1);
						System.out.println("Rows Affected: "+rowsAffected);
						System.out.println("Student with roll number: "+rollNo1+" is deleted");
					}
					else {
						System.out.println("Student with roll number: "+rollNo1+" is not found");
					}
					break;
					}
				case 6:
				{
					List<DisplayResultDTO> dtoList = studentDao.displayResult();
					showResult(dtoList);
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
	public static void displayStudent(List<Student> studentList) {
		for(Student s:studentList) {
			System.out.println("Roll Number: "+s.getRollNo());
			System.out.println("First Name:"+s.getFirstName());
			System.out.println("Last Name: "+s.getLastName());
			System.out.println("Email: "+s.getEmail());
			System.out.println("Date Of Birth: "+s.getDob());
			System.out.println("Address:"+s.getAddress());
			System.out.println("Gender: "+s.getGender());
			System.out.println("Enrollement Date: "+s.getEnrollment_date());
			System.out.println();
		}
	}
	public static void showResult(List<DisplayResultDTO> dtoList) {
		for(DisplayResultDTO dto:dtoList) {
			System.out.println("=============Student Result============");
			System.out.println("First Name: "+dto.getFirstName());
			System.out.println("Last Name: "+dto.getLastName());
			System.out.println("Semester: "+dto.getSemester());
			System.out.println("Course Code: "+dto.getCourseCode());
			System.out.println("Course Name: "+dto.getCourseName());
			System.out.println("Credits: "+dto.getCredits());
			System.out.println("Grade: "+dto.getGrade());
			System.out.println("GPA: "+dto.getGpa());
			System.out.println("=======================================");
		}
	}
}
