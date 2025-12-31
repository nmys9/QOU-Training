package com.qoutraining.taskmanagement.model.mapper;

import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.model.dto.user.UserResponseDto;
import com.qoutraining.taskmanagement.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "password",ignore = true)
    @Mapping(target = "role",ignore = true)
    User toEntity(UserRequestDto dto);

    UserResponseDto toResponseDto(User entity);

    @Mapping(target = "id",ignore = true)
    void updateEntityFromDto(UserRequestDto dto, @MappingTarget User entity);

}
