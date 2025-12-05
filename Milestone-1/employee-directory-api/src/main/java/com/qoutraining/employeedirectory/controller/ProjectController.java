package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.project.AddProjectDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesDTO;
import com.qoutraining.employeedirectory.model.dto.project.ReadProjectDTO;
import com.qoutraining.employeedirectory.service.ProjectService;
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
    public List<ReadProjectDTO> getProjects(){
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ReadProjectDTO getProject(@PathVariable Long id){
        return projectService.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<ProjectEmployeesDTO> getProjectEmployees(@PathVariable Long id){
        return projectService.findEmployeesByIdProject(id);
    }

    @PostMapping
    public ReadProjectDTO addProject(@RequestBody AddProjectDTO project){
        return projectService.addProject(project);
    }

    @PutMapping("/{id}")
    public ReadProjectDTO updateProject(@PathVariable Long id,
                                        @RequestBody AddProjectDTO update){
        return projectService.updateProject(id,update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
