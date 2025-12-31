package com.qoutraining.employeedirectory.model.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoutraining.employeedirectory.model.entity.Employee;

import java.time.LocalDate;

public record EmployeeResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String address,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate hireDate,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,
        String departmentName,
        String jobTitleName

) {

}
