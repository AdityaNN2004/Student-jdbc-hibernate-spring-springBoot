package com.studentSpringBoot.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.studentSpringBoot.dto.request.TeacherOnboardRequestDto;
import com.studentSpringBoot.dto.response.ApiResponse;
import com.studentSpringBoot.dto.response.TeacherResponse;
import com.studentSpringBoot.entity.Department;
import com.studentSpringBoot.entity.Teacher;
import com.studentSpringBoot.entity.User;
import com.studentSpringBoot.entity.UserRole;
import com.studentSpringBoot.exceptions.DepartmentNotFoundException;
import com.studentSpringBoot.exceptions.EmailAlreadyExistsException;
import com.studentSpringBoot.exceptions.TeacherProfileMissingException;
import com.studentSpringBoot.repository.DepartmentRepository;
import com.studentSpringBoot.repository.TeacherRepository;
import com.studentSpringBoot.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class TeacherServiceImpl implements TeacherService{
	private final TeacherRepository teacherRepository;
	private final UserRepository userRepository;
	private final DepartmentRepository departmentRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public List<TeacherResponse> getAllTeacher() {
		List<TeacherResponse> teacherList = teacherRepository.findAll()
				.stream()
				.map(teacher->{
					TeacherResponse res = new TeacherResponse();
					res.setUserId(teacher.getUser().getId());
					res.setDeptId(teacher.getDepartment().getId());
					res.setTeacherId(teacher.getId());
					res.setFullName(teacher.getFirstName()+" "+teacher.getMiddleName()+" "+teacher.getLastName());
					res.setEmail(teacher.getUser().getEmail());
					res.setDoj(teacher.getDoj());
					res.setSalary(teacher.getSalary());
					res.setDeptName(teacher.getDepartment().getDepartmentName());
					return res;
				})
				.toList()
				;
		
		
		return teacherList;
	}
	@Override
	public TeacherResponse onboardTeacher(TeacherOnboardRequestDto teacherOnboardRequestDto) {
		if(userRepository.existsByEmail(teacherOnboardRequestDto.getEmail())) {
			throw new EmailAlreadyExistsException("User with Email: "+teacherOnboardRequestDto.getEmail()+" is already registered");
		}
		Department dept = departmentRepository.findById(teacherOnboardRequestDto.getDepartmentId()).orElseThrow(()->new DepartmentNotFoundException("Department with ID: "+teacherOnboardRequestDto.getDepartmentId()+" is not found"));
		User user = new User();
		user.setEmail(teacherOnboardRequestDto.getEmail());
		user.setPassword(passwordEncoder.encode(teacherOnboardRequestDto.getPassword()));
		user.setUserRole(UserRole.TEACHER);
		User savedUser = userRepository.save(user);
		Teacher teacher = new Teacher();
		teacher.setFirstName(teacherOnboardRequestDto.getFirstName());
		teacher.setMiddleName(teacherOnboardRequestDto.getMiddleName());
		teacher.setLastName(teacherOnboardRequestDto.getLastName());
		teacher.setMobileNo(teacherOnboardRequestDto.getMobileNo());
		teacher.setDob(teacherOnboardRequestDto.getDob());
		teacher.setDoj(teacherOnboardRequestDto.getDoj());
		teacher.setAddress(teacherOnboardRequestDto.getAddress());
		teacher.setSalary(teacherOnboardRequestDto.getSalary());
		teacher.setDepartment(dept);
		teacher.setUser(savedUser);
		Teacher savedTeacher = teacherRepository.save(teacher);
		String fullName = teacherOnboardRequestDto.getFirstName()+" "+teacherOnboardRequestDto.getMiddleName()+" "+teacherOnboardRequestDto.getLastName();
		return new TeacherResponse(
				savedUser.getId(), 
				dept.getId(),
				savedTeacher.getId(),
				fullName, 
				teacherOnboardRequestDto.getEmail(),
				teacherOnboardRequestDto.getDoj(), 
				teacherOnboardRequestDto.getSalary(),
				"Teacher Registered successfully"
		);
	}
	@Override
	public TeacherResponse getByMobileNo(String mobileNo) {
		Teacher teacher = teacherRepository.findByMobileNo(mobileNo);
		TeacherResponse res = new TeacherResponse();
		res.setUserId(teacher.getUser().getId());
		res.setDeptId(teacher.getDepartment().getId());
		res.setTeacherId(teacher.getId());
		res.setFullName(teacher.getFirstName()+" "+teacher.getMiddleName()+" "+teacher.getLastName());
		res.setEmail(teacher.getUser().getEmail());
		res.setDoj(teacher.getDoj());
		res.setSalary(teacher.getSalary());
		res.setDeptName(teacher.getDepartment().getDepartmentName());
		return res;
	}
	@Override
	public ApiResponse editSalary(Long id,double salary) {
		Teacher teacher = teacherRepository.findById(id).
				orElseThrow(()-> new TeacherProfileMissingException("Teacher with ID: "+id+" is not found"));
		teacher.setSalary(salary);
		Teacher savedTeacher = teacherRepository.save(teacher);
		return new ApiResponse("Salary of tecaher with ID: "+id+" is updated successfully"+"\nUpdated Salary: "+savedTeacher.getSalary(), "Success");
	}
	@Override
	public List<TeacherResponse> getTeacherByDept(Long deptId) {
	    // 1. Repository gives us List<Teacher>
	    return teacherRepository.findByDepartmentId(deptId) 
	            .stream() // 2. Stream<Teacher>
	            .map((com.studentSpringBoot.entity.Teacher teacher) -> { 
	                TeacherResponse res = new TeacherResponse();
	                res.setUserId(teacher.getUser().getId());
	                res.setDeptId(teacher.getDepartment().getId());
	                res.setTeacherId(teacher.getId());
	                res.setFullName(teacher.getFirstName() + " " + teacher.getMiddleName() + " " + teacher.getLastName());
	                res.setEmail(teacher.getUser().getEmail());
	                res.setDoj(teacher.getDoj());
	                res.setSalary(teacher.getSalary());
	                res.setDeptName(teacher.getDepartment().getDepartmentName());
	                return res;
	            })
	            .toList(); // 4. Collects into List<TeacherResponse>
	}
	
}
