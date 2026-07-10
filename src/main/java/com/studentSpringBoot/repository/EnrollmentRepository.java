package com.studentSpringBoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentSpringBoot.entity.Enrollment;
import com.studentSpringBoot.entity.Student;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	List<Enrollment> findByStudentId(Long studentId);

}
