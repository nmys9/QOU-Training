package com.qoutraining.employeedirectory.controller;


import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleRequestDTO;
import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleResponseDTO;
import com.qoutraining.employeedirectory.service.JobTitleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jobTitles")
@RequiredArgsConstructor
public class JobTitleController {

    private final JobTitleService jobTitleService;

    @GetMapping
    public List<JobTitleResponseDTO> JobTitles(){
        return jobTitleService.findAll();
    }

    @GetMapping("/{id}")
    public JobTitleResponseDTO getJobTitle(@PathVariable Long id){
        return jobTitleService.findById(id);
    }

    @PostMapping
    public JobTitleResponseDTO addJobTitle(@Valid @RequestBody JobTitleRequestDTO dto){
        return jobTitleService.addJobTitle(dto);
    }

    @PutMapping("/{id}")
    public JobTitleResponseDTO updateJobTitle(@PathVariable Long id,
                                              @Valid @RequestBody JobTitleRequestDTO update){
        return jobTitleService.updateJobTitle(id,update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobTitle(@PathVariable Long id){
        jobTitleService.deleteJobTitle(id);
        return ResponseEntity.noContent().build();
    }
}
