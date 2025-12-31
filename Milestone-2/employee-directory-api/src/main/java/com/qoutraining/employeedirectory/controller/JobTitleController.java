package com.qoutraining.employeedirectory.controller;


import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleRequestDTO;
import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleResponseDTO;
import com.qoutraining.employeedirectory.service.JobTitleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jobTitles")
@RequiredArgsConstructor
public class JobTitleController {

    private final JobTitleService jobTitleService;

    @GetMapping
    public ResponseEntity<Page<JobTitleResponseDTO>> findAllJobTitles(
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC)Pageable pageable
            ){
        var response= jobTitleService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTitleResponseDTO> getJobTitle(@PathVariable Long id){
        var response = jobTitleService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<JobTitleResponseDTO> addJobTitle(@Valid @RequestBody JobTitleRequestDTO dto){
        var response= jobTitleService.addJobTitle(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobTitleResponseDTO> updateJobTitle(@PathVariable Long id,
                                              @Valid @RequestBody JobTitleRequestDTO update){
        var response = jobTitleService.updateJobTitle(id,update);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobTitle(@PathVariable Long id){
        jobTitleService.deleteJobTitle(id);
        return ResponseEntity.noContent().build();
    }
}
