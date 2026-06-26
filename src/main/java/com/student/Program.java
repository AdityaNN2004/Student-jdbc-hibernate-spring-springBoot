package com.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Program {
	private static Scanner sc = new Scanner(System.in);
	public static int menuList() {
		int choice;
		System.out.println("-------------------Menu---------------------");
		System.out.println("Enter");
		System.out.println("0 for Exit");
		System.out.println("1 for Adding a new Student");
		System.out.println("2 for Get list of Students");
		System.out.println("3 for Find a Student by Roll Number");
		System.out.println("4 for Updating Student details");
		System.out.println("5 for Deleting a Student");
		System.out.println("Enter your choice: ");
		choice = sc.nextInt();
		System.out.println("-------------------------------------------");
		return choice;
	}
	public static void main(String[] args) {
		int choice;
		Student s = new Student();
		try(StudentDaoImpl studentDao = new StudentDaoImpl())
		{
			while((choice = menuList()) !=0) {
				switch(choice) {
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
}
