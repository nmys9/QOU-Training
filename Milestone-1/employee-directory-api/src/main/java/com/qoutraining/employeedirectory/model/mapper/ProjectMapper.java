package com.qoutraining.employeedirectory.model.mapper;

import com.qoutraining.employeedirectory.model.dto.project.ProjectRequestDTO;
import com.qoutraining.employeedirectory.model.dto.project.ProjectResponseDTO;
import com.qoutraining.employeedirectory.model.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "id", ignore = true)
    Project toEntity(ProjectRequestDTO dto);

    ProjectResponseDTO toResponseDto(Project entity);

    List<ProjectResponseDTO> toResponseListDto(List<Project> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ProjectRequestDTO dto,@MappingTarget Project entity);

}
