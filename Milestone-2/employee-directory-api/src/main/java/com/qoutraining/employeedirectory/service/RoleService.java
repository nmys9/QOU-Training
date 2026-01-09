package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.role.CreateRoleRequest;
import com.qoutraining.employeedirectory.model.dto.role.RoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    RoleResponse createRoles(CreateRoleRequest request);
    Page<RoleResponse> findAllRole(Pageable pageable);
    RoleResponse findRoleById(Long id);
    RoleResponse findRoleByName(String name);
    void deleteRole(Long id);
}
