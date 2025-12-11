package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.project.ProjectRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesResponseDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectResponseDTO;
import com.qoutraining.employeedirectory.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectResponseDTO> getProjects(){
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectResponseDTO getProject(@PathVariable Long id){
        return projectService.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<ProjectEmployeesResponseDTO> getProjectEmployees(@PathVariable Long id){
        return projectService.findEmployeesByIdProject(id);
    }

    @PostMapping
    public ProjectResponseDTO addProject(@Valid @RequestBody ProjectRequestDTO project){
        return projectService.addProject(project);
    }

    @PutMapping("/{id}")
    public ProjectResponseDTO updateProject(@PathVariable Long id,
                                            @Valid @RequestBody ProjectRequestDTO update){
        return projectService.updateProject(id,update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
