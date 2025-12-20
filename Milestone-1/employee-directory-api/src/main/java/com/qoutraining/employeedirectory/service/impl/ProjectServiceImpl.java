package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesResponseDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectResponseDTO;
import com.qoutraining.employeedirectory.model.entity.Employee;
import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import com.qoutraining.employeedirectory.model.entity.Project;
import com.qoutraining.employeedirectory.model.mapper.EmployeeProjectMapper;
import com.qoutraining.employeedirectory.model.mapper.ProjectMapper;
import com.qoutraining.employeedirectory.repository.EmployeePhoneRepository;
import com.qoutraining.employeedirectory.repository.EmployeeProjectRepository;
import com.qoutraining.employeedirectory.repository.ProjectRepository;
import com.qoutraining.employeedirectory.service.EmployeeService;
import com.qoutraining.employeedirectory.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeProjectRepository employeeProjectRepository;
    private final EmployeeService employeeService;
    private final ProjectMapper projectMapper;
    private final EmployeeProjectMapper employeeProjectMapper;


    @Transactional
    private Project findProjectById(Long id){
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project cannot found with id :" + id));
    }
    @Override
    public List<ProjectResponseDTO> findAll() {
        return projectMapper.toResponseListDto(projectRepository.findAll());
    }

    @Override
    @Transactional
    public ProjectResponseDTO findById(Long id) {
        Project project=findProjectById(id);
        return projectMapper.toResponseDto(project);
    }

    @Override
    public List<ProjectEmployeesResponseDTO> findEmployeesByIdProject(Long id) {
        Project project=findProjectById(id);
        List<EmployeeProject> employees= project.getEmployeeProjects();
        return  employeeProjectMapper.toEmployeeResponseList(employees);
    }

    @Override
    @Transactional
    public ProjectResponseDTO addProject(ProjectRequestDTO dto) {
        Project project=projectMapper.toEntity(dto);
        Project savedProject=projectRepository.save(project);
        return projectMapper.toResponseDto(savedProject);
    }

    @Override
    public ProjectEmployeesResponseDTO addEmployeeToProject(Long projectId,ProjectEmployeesRequestDTO dto) {
        Project project=findProjectById(projectId);
        Employee employee=employeeService.findEmployeeByID(dto.employeeId());

        EmployeeProject employeeProject=employeeProjectMapper.toEntity(dto);

        employeeProject.setEmployee(employee);
        employeeProject.setProject(project);

        EmployeeProject savedEmployeeProject=employeeProjectRepository.save(employeeProject);
        return employeeProjectMapper.toEmployeeResponseDto(savedEmployeeProject);
    }

    @Override
    @Transactional
    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO dto) {
        Project project=findProjectById(id);
        projectMapper.updateEntityFromDto(dto,project);
        Project updateProject=projectRepository.save(project);
        return projectMapper.toResponseDto(updateProject);
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        Project project=findProjectById(id);
        projectRepository.delete(project);
    }
}
