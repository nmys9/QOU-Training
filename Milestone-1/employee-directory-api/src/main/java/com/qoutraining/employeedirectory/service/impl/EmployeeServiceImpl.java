package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectDTO;
import com.qoutraining.employeedirectory.model.dto.employee.AddEmployeeDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneDTO;
import com.qoutraining.employeedirectory.model.dto.employee.ReadEmployeeDTO;
import com.qoutraining.employeedirectory.model.dto.employee.UpdateEmployeeDTO;
import com.qoutraining.employeedirectory.model.entity.*;
import com.qoutraining.employeedirectory.repository.DepartmentRepository;
import com.qoutraining.employeedirectory.repository.EmployeePhoneRepository;
import com.qoutraining.employeedirectory.repository.EmployeeRepository;
import com.qoutraining.employeedirectory.repository.JobTitleRepository;
import com.qoutraining.employeedirectory.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final JobTitleRepository jobTitleRepository;
    private final EmployeePhoneRepository employeePhoneRepository;

    @Override
    public List<ReadEmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(ReadEmployeeDTO::fromEntity)
                .toList();
    }

    @Override
    public ReadEmployeeDTO findByID(Long id) {
        Employee employee= employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return ReadEmployeeDTO.fromEntity(employee);
    }

    public Department findDepartmentByID(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: "+ id));
    }

    public JobTitle findJobTitleById(Long id){
        return jobTitleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobTitle not found with id: "+ id));

    }

    @Override
    public List<EmployeePhone> findEmployeePhoneById(Long id) {
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return employee.getEmployeePhones();
    }

    @Override
    public List<EmployeeProjectDTO> findEmployeeProjectsById(Long id) {
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return employee.getEmployeeProjects().stream()
                .map(EmployeeProjectDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public ReadEmployeeDTO addEmployee(AddEmployeeDTO dto) {
        Department department=findDepartmentByID(dto.departmentId());


        JobTitle jobTitle=findJobTitleById(dto.jobTitleId());
        Employee newEmployee= Employee.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .address(dto.address())
                .hireDate(dto.hireDate())
                .department(department)
                .jobTitle(jobTitle)
                .build();

        Employee savedEmployee=employeeRepository.save(newEmployee);

        return ReadEmployeeDTO.fromEntity(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeePhone addEmployeePhone(Long id, EmployeePhoneDTO dto) {
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        EmployeePhone employeePhone=EmployeePhone.builder()
                .phoneNumber(dto.phoneNumber())
                .phoneType(dto.phoneType())
                .employee(employee)
                .build();

        return employeePhoneRepository.save(employeePhone);
    }

    @Override
    @Transactional
    public ReadEmployeeDTO updateEmployee(Long id, UpdateEmployeeDTO dto) {
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found wit id :" + id));

        Department department=findDepartmentByID(dto.departmentId());
        JobTitle jobTitle=findJobTitleById(dto.jobTitleId());

        employee.setFirstName(dto.firstName());
        employee.setLastName(dto.lastName());
        employee.setEmail(dto.email());
        employee.setAddress(dto.address());
        employee.setDepartment(department);
        employee.setJobTitle(jobTitle);

        Employee updateEmployee= employeeRepository.save(employee);
        
        return ReadEmployeeDTO.fromEntity(updateEmployee);
    }

    @Override
    @Transactional
    public void terminateEmployee(Long id, LocalDate endDate) {
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found wit id :" + id));

        employee.setEndDate(endDate);

        employeeRepository.save(employee);
    }





}
