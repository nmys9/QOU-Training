package com.qoutraining.employeedirectory.model.mapper;

import com.qoutraining.employeedirectory.model.dto.role.CreateRoleRequest;
import com.qoutraining.employeedirectory.model.dto.role.RoleResponse;
import com.qoutraining.employeedirectory.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "users",ignore = true)
    Role toEntity(CreateRoleRequest request);

    RoleResponse toResponse(Role entity);

}
