package com.studentSpringBoot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentSpringBoot.entity.Department;
import com.studentSpringBoot.exceptions.DepartmentNotFoundException;
import com.studentSpringBoot.repository.DepartmentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentRepository deptRepository;
	@Override
	public List<Department> getAllDepartments() {
		return deptRepository.findAll();
	}
	@Override
	public Department addDept(Department dept) {
		Department savedDept = deptRepository.save(dept);
		return savedDept;
	}
	@Override
	public Department getDeptDetails(String deptName) {
		
		return deptRepository.findByDepartmentName(deptName);
	}
	@Override
	public Department editHod(Long id, String hodName) {
		Department dept = deptRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException("Department with ID: "+id+"not found"));
		dept.setHod(hodName);
		Department updatedDept = deptRepository.save(dept);
		return updatedDept;
	}
	

}
