package com.hibernate.student.entity;

import org.hibernate.annotations.NaturalId;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter 
@ToString(exclude = {"student", "course"}) // 💡 FIXED: Exclude relationships to prevent an infinite print loop stack overflow
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="enrollments")
@Entity
@AttributeOverride(name="id", column=@Column(name="enrollment_id"))
public class Enrollment extends BaseEntity {
	
	@NaturalId
	@ManyToOne(fetch = FetchType.LAZY) // Optimized fetch strategy
	@JoinColumn(name="roll_no", referencedColumnName="roll_no", nullable = false) 
	private Student student;
	
	@ManyToOne(fetch = FetchType.LAZY) // Optimized fetch strategy
	@JoinColumn(name="course_code", referencedColumnName="course_code", nullable = false) 
	private Course course;
	
	@Column(nullable = false, length = 20) 
	private String semester;
	
	@Column(length = 2)
	private String grade; 
	
	@Column(name="enrollment_status", nullable = false, length = 20)
	private String enrollmentStatus = "Enrolled";

	public static void displayEnrollmentDetails(Enrollment enrollment) {
		System.out.println("--------------- Enrollment Details ---------------");
		System.out.println("Roll Number: " + (enrollment.getStudent() != null ? enrollment.getStudent().getRollNo() : "N/A"));
		System.out.println("Course Code: " + (enrollment.getCourse() != null ? enrollment.getCourse().getCourseCode() : "N/A"));
		System.out.println("Semester: " + enrollment.getSemester());
		System.out.println("Grade: " + (enrollment.getGrade() != null ? enrollment.getGrade() : "N/A"));
		System.out.println("Enrollment Status: " + enrollment.getEnrollmentStatus());
		System.out.println("--------------------------------------------------");
	}
}
