package com.qoutraining.employeedirectory.model.dto.jobTitle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JobTitleRequestDTO(

        @NotBlank(message = "Job Title name is required")
        @Size(min = 3, max = 50, message = "Job Title Name must be between 2 and 50 characters.")
        String name,

        @Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters, if provided.")
        String description
) {
}
