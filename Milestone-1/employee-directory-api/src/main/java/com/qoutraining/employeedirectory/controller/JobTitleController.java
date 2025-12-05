package com.qoutraining.employeedirectory.controller;


import com.qoutraining.employeedirectory.model.dto.jobTitle.AddJobTitleDTO;
import com.qoutraining.employeedirectory.model.dto.jobTitle.ReadJobTitleDTO;
import com.qoutraining.employeedirectory.service.JobTitleService;
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
    public List<ReadJobTitleDTO> JobTitles(){
        return jobTitleService.findAll();
    }

    @GetMapping("/{id}")
    public ReadJobTitleDTO getJobTitle(@PathVariable Long id){
        return jobTitleService.findById(id);
    }

    @PostMapping
    public ReadJobTitleDTO addJobTitle(@RequestBody AddJobTitleDTO dto){
        return jobTitleService.addJobTitle(dto);
    }

    @PutMapping("/{id}")
    public ReadJobTitleDTO updateJobTitle(@PathVariable Long id,
                                              @RequestBody AddJobTitleDTO update){
        return jobTitleService.updateJobTitle(id,update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobTitle(@PathVariable Long id){
        jobTitleService.deleteJobTitle(id);
        return ResponseEntity.noContent().build();
    }
}
