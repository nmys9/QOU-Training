package com.qoutraining.employeedirectory.model.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoutraining.employeedirectory.model.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProjectEmployeesRequestDTO(
        @NotNull(message = "Employee Id is required")
        Long employeeId,

        @NotNull(message = "Start Date is required")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,

        @NotNull(message = "Project status is required.")
        Status status
) {
}
