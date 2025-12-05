package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.jobTitle.AddJobTitleDTO;
import com.qoutraining.employeedirectory.model.dto.jobTitle.ReadJobTitleDTO;
import com.qoutraining.employeedirectory.model.entity.JobTitle;
import java.util.List;

public interface JobTitleService {
    List<ReadJobTitleDTO> findAll();
    ReadJobTitleDTO findById(Long id);
    ReadJobTitleDTO addJobTitle(AddJobTitleDTO dto);
    ReadJobTitleDTO updateJobTitle(Long id,AddJobTitleDTO dto);
    void deleteJobTitle(Long id);
}
