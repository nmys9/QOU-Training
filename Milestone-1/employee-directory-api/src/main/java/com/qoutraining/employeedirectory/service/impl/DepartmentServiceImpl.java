package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.department.AddDepartmentDTO;
import com.qoutraining.employeedirectory.model.dto.department.ReadDepartmentDTO;
import com.qoutraining.employeedirectory.model.entity.Department;
import com.qoutraining.employeedirectory.model.entity.Employee;
import com.qoutraining.employeedirectory.repository.DepartmentRepository;
import com.qoutraining.employeedirectory.repository.EmployeeRepository;
import com.qoutraining.employeedirectory.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<ReadDepartmentDTO> findAll() {
        return departmentRepository.findAll().stream()
                .map(ReadDepartmentDTO::fromEntity)
                .toList();
    }

    @Override
    public ReadDepartmentDTO findById(Long id) {
        Department department= departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: "+ id));
        return ReadDepartmentDTO.fromEntity(department);
    }

    @Override
    @Transactional
    public ReadDepartmentDTO addDepartment(AddDepartmentDTO dto) {
        Employee employee=employeeRepository.findById(dto.managerId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + dto.managerId()));

        if(employee.getJobTitle().getId() != 7){
            throw new IllegalArgumentException("Employee with id :"+ employee.getId() + " is not a manager");
        }
        Department newDepartment=Department.builder()
                .name(dto.name())
                .manager(employee)
                .build();
        Department department=departmentRepository.save(newDepartment);
        return ReadDepartmentDTO.fromEntity(department);
    }

    @Override
    @Transactional
    public ReadDepartmentDTO updateDepartment(Long id, AddDepartmentDTO dto) {
        Department department=departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: "+ id));

        Employee employee=employeeRepository.findById(dto.managerId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + dto.managerId()));

        if(employee.getJobTitle().getId() != 7){
            throw new IllegalArgumentException("Employee with id :"+ employee.getId() + " is not a manager");
        }

        department.setName(dto.name());
        department.setManager(employee);

        Department updateDepartment=departmentRepository.save(department);

        return ReadDepartmentDTO.fromEntity(updateDepartment);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        Department department=departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: "+ id));

        departmentRepository.delete(department);
    }
}
