package com.qoutraining.employeedirectory.model.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoutraining.employeedirectory.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProjectRequestDTO(

        @NotBlank(message = "Title is required")
        @Size(min = 10,max = 100,message = "Title must be between 10 and 100 characters.")
        String title,

        @Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters.")
        String description,

        @NotNull(message = "Start Date is required")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,

        @NotNull(message = "Project status is required.")
        Status status
) {
}
