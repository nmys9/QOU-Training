package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleRequestDTO;
import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleResponseDTO;
import com.qoutraining.employeedirectory.model.entity.JobTitle;
import com.qoutraining.employeedirectory.model.mapper.JobTitleMapper;
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
    private final JobTitleMapper jobTitleMapper;

    @Override
    public JobTitle findJobTitleById(Long id){
        return jobTitleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobTitle not found with id: "+ id));
    }

    @Override
    public List<JobTitleResponseDTO> findAll() {
        return jobTitleMapper.toResponseListDto(jobTitleRepository.findAll());
    }

    @Override
    public JobTitleResponseDTO findById(Long id) {
        JobTitle jobTitle=findJobTitleById(id);
        return jobTitleMapper.toResponseDto(jobTitle);
    }

    @Override
    @Transactional
    public JobTitleResponseDTO addJobTitle(JobTitleRequestDTO dto) {
        JobTitle newJobTitle=jobTitleMapper.toEntity(dto);
        JobTitle savedJobTitle=jobTitleRepository.save(newJobTitle);
        return jobTitleMapper.toResponseDto(savedJobTitle);
    }

    @Override
    @Transactional
    public JobTitleResponseDTO updateJobTitle(Long id, JobTitleRequestDTO dto) {
        JobTitle jobTitle=findJobTitleById(id);
        jobTitleMapper.updateEntityFromDto(dto, jobTitle);
        JobTitle updateJobTitle=jobTitleRepository.save(jobTitle);
        return jobTitleMapper.toResponseDto(updateJobTitle);
    }

    @Override
    @Transactional
    public void deleteJobTitle(Long id) {
        JobTitle jobTitle=findJobTitleById(id);
        jobTitleRepository.delete(jobTitle);
    }
}
