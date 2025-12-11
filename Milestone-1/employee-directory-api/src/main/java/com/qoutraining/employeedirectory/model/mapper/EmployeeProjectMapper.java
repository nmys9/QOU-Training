package com.qoutraining.employeedirectory.model.mapper;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectResponseDTO;
import java.util.List;

import com.qoutraining.employeedirectory.model.dto.project.ProjectEmployeesResponseDTO;
import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeProjectMapper {

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.title", target = "projectTitle")
    @Mapping(source = "project.description", target = "projectDescription")
    EmployeeProjectResponseDTO toProjectResponseDto(EmployeeProject entity);


    List<EmployeeProjectResponseDTO> toProjectResponseList(List<EmployeeProject> entities);

    @Mapping( target = "employeeName",
            expression = "java(entity.getEmployee() != null " +
                    "? entity.getEmployee().getFirstName() + \" \" + entity.getEmployee().getLastName()" +
                    " : \"No Employee Assigned\")")
    ProjectEmployeesResponseDTO toEmployeeResponseDto(EmployeeProject entity);

    List<ProjectEmployeesResponseDTO> toEmployeeResponseList(List<EmployeeProject> entities);
}
