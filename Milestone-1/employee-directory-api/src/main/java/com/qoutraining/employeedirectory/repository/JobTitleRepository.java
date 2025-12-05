package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTitleRepository extends JpaRepository<JobTitle,Long> {
}
