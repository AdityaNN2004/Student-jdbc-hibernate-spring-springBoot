package com.studentSpringBoot.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter@ToString(exclude = {"user","courses","department"})
@Entity
@Table(name="teachers")
@AttributeOverride(name="id",column = @Column(name="teacher_id"))
public class Teacher extends BaseEntity{
	@Column(name="first_name",nullable = false,length=30)
	private String firstName;
	@Column(name="middle_name",nullable = false,length = 30)
	private String middleName;
	@Column(name="last_name",nullable = false,length=30)
	private String lastName;
	@Column(name="mobile_no",nullable = false,unique = true,length=15)
	private String mobileNo;
	@Column(name="date_of_birth")
	private LocalDate dob;
	@Column(name="date_of_joining")
	private LocalDate doj;
	@Column(length=500)
	private String address;
	@Column(nullable=false)
	private double salary;
	@Column(name="document_url")
	private String documentUrl;
	@ManyToOne
	@JoinColumn(name="department_id",nullable=false)
	private Department department;
	@OneToMany(mappedBy = "teacher")
	private List<Course> courses;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
}
