package com.qoutraining.employeedirectory.model.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoutraining.employeedirectory.model.enums.Status;

import java.time.LocalDate;

public record AddProjectDTO(
        String title,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,
        Status status
) {
}
