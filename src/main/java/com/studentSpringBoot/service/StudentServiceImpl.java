package com.studentSpringBoot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
//import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	private final PasswordEncoder passwordEncoder;
	private final FileStorageService fileStorageService;
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	@Override
	public StudentResponseDto getStudentProfile(String rollNo) {
		Student student = studentRepository.findByRollNo(rollNo).orElseThrow(()-> new StudentProfileMissingException("Student with Roll Number: "+rollNo+" Not Found"));
		
		String fullName = student.getFirstName()+" "+student.getMiddleName()+" "+student.getLastName();
		String temporarySecureLink =null;
		if (student.getDocumentUrl() != null && !student.getDocumentUrl().trim().isEmpty()) {
	        // Reads private object key from MySQL, passes it to S3 presigner, and intercepts temporary web URL
	        temporarySecureLink = fileStorageService.generatePresignedUrl(student.getDocumentUrl());
	    }
		
		StudentResponseDto studentResponseDto = new StudentResponseDto(
					student.getId(),
					fullName,
					student.getUser().getEmail(),
					student.getRollNo(),
					student.getDepartment().getId(),
					student.getDepartment().getDepartmentName(),
					temporarySecureLink
				);
		return studentResponseDto;
	}
	@Override
	public Student registerStudent(StudentSignUpRequestDto signUpRequestDto,MultipartFile documentFile ,Long deptId) {
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
		user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
		user.setUserRole(UserRole.STUDENT);
		User savedUser = userRepository.save(user);
		Student student = new Student();
		student.setFirstName(signUpRequestDto.getFirstName());
		student.setMiddleName(signUpRequestDto.getMiddleName());
		student.setLastName(signUpRequestDto.getLastName());
		student.setMobileNo(signUpRequestDto.getMobileNo());
		student.setRollNo(signUpRequestDto.getRollNo());
		student.setEnrollmentDate(LocalDate.now());
		student.setAddress(signUpRequestDto.getAddress());
		student.setDob(signUpRequestDto.getDob());
		
		student.setDepartment(dept);
		student.setUser(savedUser);
		if (documentFile != null && !documentFile.isEmpty()) {
		    // Stores file in S3 and returns the key string (NOT the full web URL)
		    String objectKey = fileStorageService.storeFile(documentFile);
		    
		    // Saves "documents/uuid_filename.pdf" directly to the MySQL database column
		    student.setDocumentUrl(objectKey); 
		}
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
