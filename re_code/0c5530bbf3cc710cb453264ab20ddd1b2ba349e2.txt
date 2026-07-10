package com.studentSpringBoot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.studentSpringBoot.dto.request.StudentSignUpRequestDto;
import com.studentSpringBoot.dto.request.UpdateStudentContactRequestDto;
import com.studentSpringBoot.dto.response.ApiResponse;
import com.studentSpringBoot.dto.response.StudentResponseDto;
import com.studentSpringBoot.entity.Department;
import com.studentSpringBoot.entity.Student;
import com.studentSpringBoot.entity.User;
import com.studentSpringBoot.entity.UserRole;
import com.studentSpringBoot.exceptions.ApiException;
import com.studentSpringBoot.exceptions.DepartmentNotFoundException;
import com.studentSpringBoot.exceptions.EmailAlreadyExistsException;
import com.studentSpringBoot.exceptions.RollNumberAlreadyExistsException;
import com.studentSpringBoot.exceptions.StudentProfileMissingException;
import com.studentSpringBoot.repository.DepartmentRepository;
import com.studentSpringBoot.repository.StudentRepository;
import com.studentSpringBoot.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
	private final StudentRepository studentRepository;
	private final DepartmentRepository departmentRepository;
	private final UserRepository userRepository;
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	@Override
	public StudentResponseDto findByRollNumber(String rollNo) {
		Optional<Student> student = studentRepository.findByRollNo(rollNo);
		if(student.isEmpty()) {
			throw  new StudentProfileMissingException("Student with Roll Number: "+rollNo+" Not Found");
		}
		String fullName = student.get().getFirstName()+" "+student.get().getMiddleName()+" "+student.get().getLastName();
		
		StudentResponseDto studentResponseDto = new StudentResponseDto(
					student.get().getId(),
					fullName,
					student.get().getUser().getEmail(),
					student.get().getRollNo(),
					student.get().getDepartment().getId(),
					student.get().getDepartment().getDepartmentName()
				);
		return studentResponseDto;
	}
	@Override
	public Student registerStudent(StudentSignUpRequestDto signUpRequestDto, Long deptId) {
		if(userRepository.existsByEmail(signUpRequestDto.getEmail())) {
			throw new EmailAlreadyExistsException("Email: "+signUpRequestDto.getEmail()+" is already registered.");
		}
		if(studentRepository.existsByRollNo(signUpRequestDto.getRollNo())) {
			throw new RollNumberAlreadyExistsException("Roll Number: "+signUpRequestDto.getRollNo()+"is already exits");
		}
		Department dept = departmentRepository.findById(deptId).
									orElseThrow(()-> new DepartmentNotFoundException("Department with ID: "+deptId+" is not found"));
		User user = new User();
		user.setEmail(signUpRequestDto.getEmail());
		user.setPassword(signUpRequestDto.getPassword());
		user.setUserRole(UserRole.STUDENT);
		User savedUser = userRepository.save(user);
		Student student = new Student();
		student.setFirstName(signUpRequestDto.getFirstName());
		student.setLastName(signUpRequestDto.getLastName());
		student.setMiddleName(signUpRequestDto.getMiddleName());
		student.setMobileNo(signUpRequestDto.getMobileNo());
		student.setEnrollmentDate(LocalDate.now());
		student.setAddress(signUpRequestDto.getAddress());
		student.setDob(signUpRequestDto.getDob());
		
		student.setDepartment(dept);
		student.setUser(savedUser);
		
		return studentRepository.save(student);
	}
	
	@Override
	public ApiResponse editStudentContact(UpdateStudentContactRequestDto updateStudentContactRequestDto) {
		Student student = studentRepository.findByRollNo(updateStudentContactRequestDto.getRollNo()).orElseThrow(()->new StudentProfileMissingException("Student with Roll No: "+updateStudentContactRequestDto.getRollNo()+"Not found"));
		student.setAddress(updateStudentContactRequestDto.getAddress());
		student.setMobileNo(updateStudentContactRequestDto.getMobileNo());
		Student updatedStudent = studentRepository.save(student);
		return new ApiResponse("Student Contact Details Updated Successfully", "Success");
	}
	@Override
	public ApiResponse deleteStudent(Long id) {
		studentRepository.deleteById(id);
		return new ApiResponse("Student with ID: "+id+" is deleted", "Success");
	}
}
