package com.qoutraining.taskmanagement.model.mapper;

import com.qoutraining.taskmanagement.model.dto.task.TaskRequestDto;
import com.qoutraining.taskmanagement.model.dto.task.TaskResponseDto;
import com.qoutraining.taskmanagement.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "user",ignore = true)
    Task toEntity(TaskRequestDto dto);

    TaskResponseDto toResponseDto(Task entity);

    List<TaskResponseDto> toResponseList(List<Task> entities);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "user",ignore = true)
    void updateEntityFromDto(TaskRequestDto dto, @MappingTarget Task entity);
}
