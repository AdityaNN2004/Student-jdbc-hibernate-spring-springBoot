package com.hibernate.student.entity;

import java.util.List;
import java.util.Scanner;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString(exclude = "enrollment")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="courses")
@AttributeOverride(name="id", column=@Column(name="course_id"))
public class Course extends BaseEntity {
	@NaturalId
	@Column(name="course_code", length=7, nullable = false, unique = true)
	private String courseCode;
	
	@Column(name="course_name", length=100, nullable = false)
	private String courseName;
	
	@Column(name="department", length=50, nullable = false) 
	private String department;
	
	private int credits;
	
	@Column(name="is_active", nullable = false)
	private boolean isActive = true;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Enrollment> enrollment;

	public static void displayCourseDetails(Course c) {
		System.out.println("---------------Course Details-------------------");
		System.out.println("Course Code: " + c.getCourseCode());
		System.out.println("Course Name: " + c.getCourseName());
		System.out.println("Department: " + c.getDepartment());
		System.out.println("Credits: " + c.getCredits());
		System.out.println("------------------------------------------------");
	}

	// 💡 FIXED: Pass the central execution Scanner instance as a parameter instead of spawning a new one
	public void acceptCourseDetails(Scanner sc) {
		try {
			System.out.println("---------------Enter Course Details-------------------");
			System.out.println("Enter Course Code: ");
			this.setCourseCode(sc.nextLine());
			
			System.out.println("Enter Course Name: ");
			this.setCourseName(sc.nextLine());
			
			System.out.println("Enter Department Name:");
			this.setDepartment(sc.nextLine());
			
			// 💡 FIXED: Read via nextLine() and parse into an integer to clear the trailing stream token safely
			System.out.println("Enter Credits: ");
			this.setCredits(Integer.parseInt(sc.nextLine()));
			
			System.out.println("------------------------------------------------------");
		} catch(Exception e) {
			System.err.println("Error parsing course inputs. Check your formats.");
			e.printStackTrace();
		}
	}
}
