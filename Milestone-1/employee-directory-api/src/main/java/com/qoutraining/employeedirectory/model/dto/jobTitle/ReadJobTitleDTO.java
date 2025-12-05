package com.qoutraining.employeedirectory.model.dto.jobTitle;

import com.qoutraining.employeedirectory.model.entity.JobTitle;

public record ReadJobTitleDTO(
        Long id,
        String name,
        String description
) {
    public static ReadJobTitleDTO fromEntity(JobTitle jobTitle){
        return new ReadJobTitleDTO(
                jobTitle.getId(),
                jobTitle.getName(),
                jobTitle.getDescription()
        );
    }
}
