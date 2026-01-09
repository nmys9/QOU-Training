package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.role.CreateRoleRequest;
import com.qoutraining.employeedirectory.model.dto.role.RoleResponse;
import com.qoutraining.employeedirectory.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<Page<RoleResponse>> findAllRoles(
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ){
        var response=roleService.findAllRole(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RoleResponse> findRoleById(
            @PathVariable Long id){
        var response= roleService.findRoleById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RoleResponse> findRoleByName(
            @PathVariable String name){
        var response= roleService.findRoleByName(name);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(
            @Valid @RequestBody CreateRoleRequest request){
        var response=roleService.createRoles(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(
            @PathVariable Long id){
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
