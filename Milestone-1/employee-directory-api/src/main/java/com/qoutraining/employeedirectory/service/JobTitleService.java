package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleRequestDTO;
import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleResponseDTO;
import com.qoutraining.employeedirectory.model.entity.JobTitle;

import java.util.List;

public interface JobTitleService {
    List<JobTitleResponseDTO> findAll();
    JobTitleResponseDTO findById(Long id);
    JobTitle findJobTitleById(Long id);
    JobTitleResponseDTO addJobTitle(JobTitleRequestDTO dto);
    JobTitleResponseDTO updateJobTitle(Long id, JobTitleRequestDTO dto);
    void deleteJobTitle(Long id);
}
