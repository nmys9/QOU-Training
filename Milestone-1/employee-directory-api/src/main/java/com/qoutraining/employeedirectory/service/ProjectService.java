package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesResponseDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectResponseDTO;
import java.util.List;

public interface ProjectService {
    List<ProjectResponseDTO> findAll();
    ProjectResponseDTO findById(Long id);
    List<ProjectEmployeesResponseDTO> findEmployeesByIdProject(Long id);
    ProjectResponseDTO addProject(ProjectRequestDTO dto);
    ProjectEmployeesResponseDTO addEmployeeToProject(Long projectId,ProjectEmployeesRequestDTO dto);
    ProjectResponseDTO updateProject(Long id, ProjectRequestDTO dto);
    void deleteProject(Long id);
}
