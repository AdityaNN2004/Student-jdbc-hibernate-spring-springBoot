package com.hibernate.student.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter 
@Setter 
@ToString(exclude = "enrollment") 
@NoArgsConstructor
@AllArgsConstructor
@Table(name="students")
@AttributeOverride(name="id", column=@Column(name="student_id"))
public class Student extends BaseEntity {
	
	@Column(name = "roll_no", length = 6, nullable = false, unique = true) 
	private String rollNo;
	
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	
	@Column(length = 100, unique = true)
	private String email;
	
	@Column(name="date_of_birth")
	private LocalDate dob;
	
	private String address; 
	
	private double gpa;
	
	@Column(length=1)
	private String gender; 
	
	@Column(name="enrollment_date")
	private LocalDate enrollmentDate;
	
	@Column(name="is_deleted")
	private boolean isDeleted = false;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL) 
	private List<Enrollment> enrollment;
	
	// 💡 FIXED: Pass the existing scanner from Main instead of creating and closing a new one
	public void acceptStudentDetails(Scanner sc) {
		try {
			System.out.println("Enter Student details");
			System.out.println("Enter Roll Number: ");
			this.setRollNo(sc.nextLine());
			
			System.out.println("Enter First Name: ");
			this.setFirstName(sc.nextLine());
			
			System.out.println("Enter Last Name:");
			this.setLastName(sc.nextLine());
			
			System.out.println("Enter Email:");
			this.setEmail(sc.nextLine());
			
			System.out.println("Enter Date of Birth in format YYYY-MM-DD:");
			this.setDob(LocalDate.parse(sc.nextLine()));
			
			System.out.println("Enter Address:");
			this.setAddress(sc.nextLine());
			
			// 💡 FIXED: Read as nextLine() and parse to safely consume the newline token
			System.out.println("Enter GPA:");
			this.setGpa(Double.parseDouble(sc.nextLine()));
			
			System.out.println("Enter your Gender:");
			this.setGender(sc.nextLine());
			
			System.out.println("Enter Enrollment Date in format YYYY-MM-DD:");
			this.setEnrollmentDate(LocalDate.parse(sc.nextLine()));
		}
		catch(Exception e) {
			System.err.println("Error parsing student inputs. Check your formats.");
			e.printStackTrace();
		}
	}

	public static void displayStudentDetails(Student s) {
		System.out.println("------------Student Details----------------");
		System.out.println("Roll Number: " + s.getRollNo());
		System.out.println("First Name: " + s.getFirstName());
		System.out.println("Last Name: " + s.getLastName());
		System.out.println("Email: " + s.getEmail());
		System.out.println("Date Of Birth: " + s.getDob());
		System.out.println("Address: " + s.getAddress());
		System.out.println("Gender: " + s.getGender());
		System.out.println("Enrollment Date: " + s.getEnrollmentDate());
		System.out.println("-------------------------------------------");
	}
}
