package com.studentSpringBoot.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherOnboardRequestDto {
	private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNo;
    private LocalDate dob;
    private LocalDate doj;
    private String address;
    private double salary;
    private Long departmentId;
}
