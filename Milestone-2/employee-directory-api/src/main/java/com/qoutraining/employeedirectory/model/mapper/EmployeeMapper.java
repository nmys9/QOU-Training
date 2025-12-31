package com.qoutraining.employeedirectory.model.mapper;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeResponseDTO;
import com.qoutraining.employeedirectory.model.entity.Employee;
import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    Employee toEntity(EmployeeRequestDTO dto);

    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "jobTitle.name", target = "jobTitleName")
    EmployeeResponseDTO toResponseDto(Employee entity);

    List<EmployeeResponseDTO> toResponseListDto(List<Employee> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    void updateEntityFromDto(EmployeeRequestDTO dto,@MappingTarget Employee entity);

}
