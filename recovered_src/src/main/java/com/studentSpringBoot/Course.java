package com.studentSpringBoot.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@ToString(exclude = {"department", "teacher"}) 
@Entity
@AttributeOverride(name="id", column = @Column(name="course_id"))
@Table(name="courses")
public class Course extends BaseEntity {

	@Column(name="course_code", length=6, nullable = false, unique = true) 
	private String courseCode;
	
	@Column(name="course_name", nullable = false)
	private String courseName;
	
	private int credits;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
}
