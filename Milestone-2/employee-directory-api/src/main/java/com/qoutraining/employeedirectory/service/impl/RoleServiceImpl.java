package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.DuplicateResourceException;
import com.qoutraining.employeedirectory.exception.OperationNotAllowedException;
import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.role.CreateRoleRequest;
import com.qoutraining.employeedirectory.model.dto.role.RoleResponse;
import com.qoutraining.employeedirectory.model.entity.Role;
import com.qoutraining.employeedirectory.model.mapper.RoleMapper;
import com.qoutraining.employeedirectory.repository.RoleRepository;
import com.qoutraining.employeedirectory.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional
    public RoleResponse createRoles(CreateRoleRequest request) {
        if(roleRepository.existsRoleByName(request.name())){
            throw new DuplicateResourceException("Role with name " + request.name() + " already exists.");
        }
        Role role=roleMapper.toEntity(request);
        roleRepository.save(role);
        return roleMapper.toResponse(role);
    }

    @Override
    public Page<RoleResponse> findAllRole(Pageable pageable) {
        return roleRepository.findAll(pageable)
                .map(roleMapper::toResponse);
    }

    @Override
    public RoleResponse findRoleById(Long id) {
        Role role=roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Role not found with Id: "+ id)
        );
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse findRoleByName(String name) {
        Role role=roleRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Role not found with name: "+ name)
        );
        return roleMapper.toResponse(role);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        Role role=roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Role not found with Id: "+ id)
        );

        if(role.getUsers() != null && !role.getUsers().isEmpty()){
            throw new OperationNotAllowedException("Cannot delete role " + role.getName() + "" +
                    " because it is assigned to " + role.getUsers().size() + " user(s)");
        }
        roleRepository.delete(role);
    }
}
