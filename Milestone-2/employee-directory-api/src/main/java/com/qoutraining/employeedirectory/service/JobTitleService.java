package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleRequestDTO;
import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleResponseDTO;
import com.qoutraining.employeedirectory.model.entity.JobTitle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobTitleService {
    Page<JobTitleResponseDTO> findAll(Pageable pageable);
    JobTitleResponseDTO findById(Long id);
    JobTitle findJobTitleById(Long id);
    JobTitleResponseDTO addJobTitle(JobTitleRequestDTO dto);
    JobTitleResponseDTO updateJobTitle(Long id, JobTitleRequestDTO dto);
    void deleteJobTitle(Long id);
}
