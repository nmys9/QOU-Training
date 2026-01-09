package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.DuplicateResourceException;
import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.department.DepartmentRequestDTO;
import com.qoutraining.employeedirectory.model.dto.department.DepartmentResponseDTO;
import com.qoutraining.employeedirectory.model.entity.Department;
import com.qoutraining.employeedirectory.model.mapper.DepartmentMapper;
import com.qoutraining.employeedirectory.repository.DepartmentRepository;
import com.qoutraining.employeedirectory.repository.EmployeeRepository;
import com.qoutraining.employeedirectory.service.DepartmentService;
import com.qoutraining.employeedirectory.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentMapper departmentMapper;

    private Department findDepartmentByID(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: "+ id));
    }

    @Override
    public Page<DepartmentResponseDTO> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable).map(departmentMapper::toResponseDto);
    }

    @Override
    public DepartmentResponseDTO findById(Long id) {
        Department department= findDepartmentByID(id);
        return departmentMapper.toResponseDto(department);
    }

    @Override
    @Transactional
    public DepartmentResponseDTO addDepartment(DepartmentRequestDTO dto) {
        if(departmentRepository.existsDepartmentByName(dto.name())){
            throw new DuplicateResourceException("Department with name: " + dto.name() +" already exists.");
        }
        Department newDepartment=departmentMapper.toEntity(dto);
        Department savedDepartment=departmentRepository.save(newDepartment);
        return departmentMapper.toResponseDto(savedDepartment);
    }

    @Override
    @Transactional
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO dto) {
        Department department=findDepartmentByID(id);
        departmentMapper.updateEntityFromDto(dto, department);
        Department updateDepartment=departmentRepository.save(department);
        return departmentMapper.toResponseDto(updateDepartment);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        Department department=findDepartmentByID(id);
        employeeRepository.detachEmployeesFromDepartment(id);
        departmentRepository.delete(department);
    }
}
