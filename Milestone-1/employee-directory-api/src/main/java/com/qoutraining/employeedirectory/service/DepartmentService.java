package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.department.DepartmentRequestDTO;
import com.qoutraining.employeedirectory.model.dto.department.DepartmentResponseDTO;
import com.qoutraining.employeedirectory.model.entity.Department;

import java.util.List;


public interface DepartmentService {
    List<DepartmentResponseDTO> findAll();
    DepartmentResponseDTO findById(Long id);
    DepartmentResponseDTO addDepartment(DepartmentRequestDTO dto);
    DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO dto);
    void deleteDepartment(Long id);
}
