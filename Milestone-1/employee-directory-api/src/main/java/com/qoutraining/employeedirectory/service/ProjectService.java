package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectDTO;
import com.qoutraining.employeedirectory.model.dto.project.AddProjectDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesDTO;
import com.qoutraining.employeedirectory.model.dto.project.ReadProjectDTO;
import java.util.List;

public interface ProjectService {
    List<ReadProjectDTO> findAll();
    ReadProjectDTO findById(Long id);
    List<ProjectEmployeesDTO> findEmployeesByIdProject(Long id);
    ReadProjectDTO addProject(AddProjectDTO dto);
    ReadProjectDTO updateProject(Long id, AddProjectDTO dto);
    void deleteProject(Long id);
}
