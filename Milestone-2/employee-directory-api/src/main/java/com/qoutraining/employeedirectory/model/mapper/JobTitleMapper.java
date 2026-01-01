package com.qoutraining.employeedirectory.model.mapper;

import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleRequestDTO;
import com.qoutraining.employeedirectory.model.dto.jobTitle.JobTitleResponseDTO;
import com.qoutraining.employeedirectory.model.entity.JobTitle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobTitleMapper {

    @Mapping(target = "id",ignore = true)
    JobTitle toEntity(JobTitleRequestDTO dto);

    JobTitleResponseDTO toResponseDto(JobTitle entity);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(JobTitleRequestDTO dto, @MappingTarget JobTitle entity);
}
