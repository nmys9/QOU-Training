package com.qoutraining.employeedirectory.model.mapper;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employee.UpdateEmployeeDTO;
import com.qoutraining.employeedirectory.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "jobTitle.name", target = "jobTitleName")
    EmployeeResponseDTO toResponseDto(Employee entity);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    void updateEntityFromDto(UpdateEmployeeDTO dto, @MappingTarget Employee entity);

}
