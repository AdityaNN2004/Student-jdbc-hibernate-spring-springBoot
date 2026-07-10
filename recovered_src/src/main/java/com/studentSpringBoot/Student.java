package com.studentSpringBoot.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@Setter
@Getter
@ToString(exclude = {"user", "enrollements", "department"})
@Entity
@Table(name="students")
@SoftDelete(columnName = "is_deleted",strategy = SoftDeleteType.DELETED)
@AttributeOverride(name="id", column = @Column(name="reg_no"))
public class Student extends BaseEntity {

	@Column(name="first_name", nullable = false, length=30)
	private String firstName;
	@Column(name="middle_name",nullable = false,length=30)
	private String middleName;
	@Column(name="last_name", nullable = false, length=30)
	private String lastName;
	
	@Column(name="mobile_no", nullable = false, unique = true, length=15)
	private String mobileNo;
	
	@Column(name="roll_no", nullable = false, unique = true, length=6)
	private String rollNo;
	
	@OneToOne
	@JoinColumn(name="department_id", nullable=false)
	private Department department;
	
	@Column(length=500)
	private String address;
	
	@Column(name="date_of_birth")
	private LocalDate dob;
	
	@Column(name="enrollment_date")
	private LocalDate enrollmentDate;
	
	@Column(name="document_url")
	private String documentUrl;
	
	@OneToOne
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "student")
	private List<Enrollment> enrollements;
}
