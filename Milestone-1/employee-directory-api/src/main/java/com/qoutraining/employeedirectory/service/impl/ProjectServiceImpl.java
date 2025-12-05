package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectDTO;
import com.qoutraining.employeedirectory.model.dto.project.AddProjectDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesDTO;
import com.qoutraining.employeedirectory.model.dto.project.ReadProjectDTO;
import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import com.qoutraining.employeedirectory.model.entity.Project;
import com.qoutraining.employeedirectory.repository.ProjectRepository;
import com.qoutraining.employeedirectory.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<ReadProjectDTO> findAll() {
        return projectRepository.findAll().stream()
                .map(ReadProjectDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public ReadProjectDTO findById(Long id) {
        Project project=existsProject(id);
        return ReadProjectDTO.fromEntity(project);
    }

    @Override
    public List<ProjectEmployeesDTO> findEmployeesByIdProject(Long id) {
        Project project=existsProject(id);

        return project.getEmployeeProjects().stream()
                .map(ProjectEmployeesDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public ReadProjectDTO addProject(AddProjectDTO dto) {
        Project project=Project.builder()
                .title(dto.title())
                .description(dto.description())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .status(dto.status())
                .build();

        Project newProject=projectRepository.save(project);

        return ReadProjectDTO.fromEntity(newProject);
    }

    @Override
    @Transactional
    public ReadProjectDTO updateProject(Long id, AddProjectDTO dto) {
        Project project=existsProject(id);

        project.setTitle(dto.title());
        project.setDescription(dto.description());
        project.setStartDate(dto.startDate());
        project.setEndDate(dto.endDate());
        project.setStatus(dto.status());

        Project updateProject=projectRepository.save(project);

        return ReadProjectDTO.fromEntity(updateProject);
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        Project project=existsProject(id);
        projectRepository.delete(project);
    }

    public Project existsProject(Long id){
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project cannot found with id :" + id));
    }
}
