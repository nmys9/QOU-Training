package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.jobTitle.AddJobTitleDTO;
import com.qoutraining.employeedirectory.model.dto.jobTitle.ReadJobTitleDTO;
import com.qoutraining.employeedirectory.model.entity.JobTitle;
import com.qoutraining.employeedirectory.repository.JobTitleRepository;
import com.qoutraining.employeedirectory.service.JobTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobTitleServiceImpl implements JobTitleService {

    private final JobTitleRepository jobTitleRepository;

    @Override
    public List<ReadJobTitleDTO> findAll() {
        return jobTitleRepository.findAll().stream()
                .map(ReadJobTitleDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public ReadJobTitleDTO findById(Long id) {
        JobTitle jobTitle= jobTitleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobTitle not found with id: "+ id));
        return ReadJobTitleDTO.fromEntity(jobTitle);
    }

    @Override
    @Transactional
    public ReadJobTitleDTO addJobTitle(AddJobTitleDTO dto) {
        JobTitle newJobTitle=JobTitle.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
        newJobTitle=jobTitleRepository.save(newJobTitle);
        return ReadJobTitleDTO.fromEntity(newJobTitle);
    }

    @Override
    @Transactional
    public ReadJobTitleDTO updateJobTitle(Long id, AddJobTitleDTO dto) {
        JobTitle jobTitle=jobTitleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobTitle not found with id: "+ id));

        jobTitle.setName(dto.name());
        jobTitle.setDescription(dto.description());

        JobTitle updateJobTitle=jobTitleRepository.save(jobTitle);

        return ReadJobTitleDTO.fromEntity(updateJobTitle);
    }

    @Override
    @Transactional
    public void deleteJobTitle(Long id) {
        JobTitle jobTitle=jobTitleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobTitle not found with id: "+ id));
         jobTitleRepository.delete(jobTitle);
    }
}
