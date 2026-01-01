package com.qoutraining.employeedirectory.model.mapper;

import com.qoutraining.employeedirectory.model.dto.department.DepartmentRequestDTO;
import com.qoutraining.employeedirectory.model.dto.department.DepartmentResponseDTO;
import com.qoutraining.employeedirectory.model.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "manager", ignore = true)
    Department toEntity(DepartmentRequestDTO dto);

    @Mapping(source = "manager.fullName", target = "managerName")
    DepartmentResponseDTO toResponseDto(Department entity);

    List<DepartmentResponseDTO> toResponseListDto(List<Department> entities);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "manager", ignore = true)
    void updateEntityFromDto(DepartmentRequestDTO dto, @MappingTarget Department entity);

}
