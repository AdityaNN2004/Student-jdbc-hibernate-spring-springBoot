package com.studentSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentSpringBoot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

}
