package com.studentSpringBoot.service;

import java.util.List;

import com.studentSpringBoot.entity.Department;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department addDept(Department dept);

    Department getDeptDetails(String deptName);

    Department editHod(Long id, String hodName);
}