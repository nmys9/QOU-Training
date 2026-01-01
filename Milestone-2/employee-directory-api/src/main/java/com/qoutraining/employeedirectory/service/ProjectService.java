package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesResponseDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    Page<ProjectResponseDTO> findAll(Pageable pageable);
    ProjectResponseDTO findById(Long id);
    Page<ProjectEmployeesResponseDTO> findEmployeesByIdProject(Long projectId,Pageable pageable);
    ProjectResponseDTO addProject(ProjectRequestDTO dto);
    ProjectEmployeesResponseDTO addEmployeeToProject(Long projectId,ProjectEmployeesRequestDTO dto);
    ProjectResponseDTO updateProject(Long id, ProjectRequestDTO dto);
    void deleteProject(Long id);
}
