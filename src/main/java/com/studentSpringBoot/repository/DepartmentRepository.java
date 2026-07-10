package com.studentSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentSpringBoot.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByDepartmentName(String deptName);

}
