package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.department.AddDepartmentDTO;
import com.qoutraining.employeedirectory.model.dto.department.ReadDepartmentDTO;

import java.util.List;


public interface DepartmentService {
    List<ReadDepartmentDTO> findAll();
    ReadDepartmentDTO findById(Long id);
    ReadDepartmentDTO addDepartment(AddDepartmentDTO dto);
    ReadDepartmentDTO updateDepartment(Long id, AddDepartmentDTO dto);
    void deleteDepartment(Long id);
}
