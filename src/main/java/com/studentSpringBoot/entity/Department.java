package com.studentSpringBoot.entity;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = {"courses"})
@Entity
@Table(name="departments")
@AttributeOverride(name="id", column = @Column(name="department_id"))
public class Department extends BaseEntity {

	@Column(name="department_name", nullable = false, unique = true, length = 50)
	private String departmentName;
	
	@Column(name="head_of_department", length = 60)
	private String hod;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<Course> courses;
}
