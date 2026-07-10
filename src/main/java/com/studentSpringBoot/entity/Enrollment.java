package com.studentSpringBoot.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude={"course", "student", "department"})
@Entity
@AttributeOverride(name="id", column = @Column(name="enrollment_id"))
@Table(name="enrollments")
public class Enrollment extends BaseEntity { 

	@Column(nullable = false)
	private String semesterCode;
	
	private double gpa;
	
	@Column(name = "enrollment_status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private EnrollmentStatus enrollmentStatus ;

	
	@ManyToOne
	@JoinColumn(name = "student_reg_no", nullable = false) 
	private Student student;


	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;
}
