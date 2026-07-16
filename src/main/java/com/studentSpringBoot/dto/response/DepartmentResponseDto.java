package com.studentSpringBoot.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentResponseDto {
    private Long id;
    private String departmentName;
    private String hod;
}