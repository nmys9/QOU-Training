package com.qoutraining.employeedirectory.model.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoutraining.employeedirectory.model.entity.Employee;

import java.time.LocalDate;

public record ReadEmployeeDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String address,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate hireDate,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,
        String department,
        String jobTitle

) {
    public static ReadEmployeeDTO fromEntity(Employee employee){
        return new ReadEmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getHireDate(),
                employee.getEndDate(),
                employee.getDepartment().getName(),
                employee.getJobTitle().getName()
        );
    }
}
