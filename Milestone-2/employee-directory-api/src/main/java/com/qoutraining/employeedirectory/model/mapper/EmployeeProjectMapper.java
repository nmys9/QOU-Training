package com.qoutraining.employeedirectory.model.mapper;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectResponseDTO;
import java.util.List;

import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesResponseDTO;
import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeProjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee",ignore = true)
    @Mapping(target = "project",ignore = true)
    EmployeeProject toEntity(ProjectEmployeesRequestDTO dto);

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.title", target = "projectTitle")
    @Mapping(source = "project.description", target = "projectDescription")
    EmployeeProjectResponseDTO toProjectResponseDto(EmployeeProject entity);


    @Mapping(source = "employee.fullName", target = "employeeName")
    ProjectEmployeesResponseDTO toEmployeeResponseDto(EmployeeProject entity);
}
