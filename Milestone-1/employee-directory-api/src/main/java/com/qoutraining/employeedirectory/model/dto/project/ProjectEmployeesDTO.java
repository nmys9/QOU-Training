package com.qoutraining.employeedirectory.model.dto.project;

import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import com.qoutraining.employeedirectory.model.entity.Project;
import com.qoutraining.employeedirectory.model.enums.Status;

import java.time.LocalDate;

public record ProjectEmployeesDTO(
        Long id,
        String employeeName,
        LocalDate startDate,
        LocalDate endDate,
        Status status
) {
    public static ProjectEmployeesDTO fromEntity(EmployeeProject employeeProject){
        String fullName=employeeProject.getEmployee().getFirstName() +
                " " + employeeProject.getEmployee().getLastName();

        return new ProjectEmployeesDTO(
                employeeProject.getId(),
                fullName,
                employeeProject.getStartDate(),
                employeeProject.getEndDate(),
                employeeProject.getStatus()
        );

    }
}
