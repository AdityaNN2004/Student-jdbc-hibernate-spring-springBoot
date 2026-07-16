package com.studentSpringBoot.entity;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = {"password","confirmPassword"})
@NoArgsConstructor
@Table(name="users")
@AttributeOverride(name = "id",column = @Column(name="user_id"))
@SoftDelete(columnName = "is_deleted", strategy = SoftDeleteType.DELETED) 
public class User extends BaseEntity{
	
	@Column(nullable = false,unique = true,length=80)
	private String email;
	@Column(nullable = false,length=400)
	private String password;
	@Transient
	private String confirmPassword;
	@Column(name="user_role")
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
}
