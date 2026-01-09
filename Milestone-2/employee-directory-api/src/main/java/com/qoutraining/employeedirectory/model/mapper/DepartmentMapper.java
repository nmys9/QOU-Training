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
    Department toEntity(DepartmentRequestDTO dto);

    DepartmentResponseDTO toResponseDto(Department entity);

    @Mapping(target = "id",ignore = true)
    void updateEntityFromDto(DepartmentRequestDTO dto, @MappingTarget Department entity);

}
