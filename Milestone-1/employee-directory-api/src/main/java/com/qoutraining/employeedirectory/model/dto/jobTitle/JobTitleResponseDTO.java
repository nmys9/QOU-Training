package com.qoutraining.employeedirectory.model.dto.jobTitle;

import com.qoutraining.employeedirectory.model.entity.JobTitle;

public record JobTitleResponseDTO(
        Long id,
        String name,
        String description
) {
}
