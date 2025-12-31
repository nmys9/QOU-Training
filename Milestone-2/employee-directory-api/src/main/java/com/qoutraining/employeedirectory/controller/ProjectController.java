package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesResponseDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectResponseDTO;
import com.qoutraining.employeedirectory.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDTO>> findAllProjects(
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC)Pageable pageable
            ){
        var response= projectService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id){
        var response=projectService.findById(id);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/{id}/employees")
//    public ResponseEntity<List<ProjectEmployeesResponseDTO>> getProjectEmployees(@PathVariable Long id){
//        var response= projectService.findEmployeesByIdProject(id);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> addProject(@Valid @RequestBody ProjectRequestDTO project){
        var response=projectService.addProject(project);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{projectId}/employee")
    public ResponseEntity<ProjectEmployeesResponseDTO> addEmployeesToProject (
            @PathVariable Long projectId,
            @RequestBody ProjectEmployeesRequestDTO dto){
        var response= projectService.addEmployeeToProject(projectId,dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id,
                                            @Valid @RequestBody ProjectRequestDTO update){
        var response= projectService.updateProject(id,update);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
