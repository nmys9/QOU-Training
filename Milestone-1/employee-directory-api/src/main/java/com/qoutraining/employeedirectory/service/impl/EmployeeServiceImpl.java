package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneResponseDTO;
import com.qoutraining.employeedirectory.model.entity.*;
import com.qoutraining.employeedirectory.model.mapper.EmployeeMapper;
import com.qoutraining.employeedirectory.model.mapper.EmployeePhoneMapper;
import com.qoutraining.employeedirectory.model.mapper.EmployeeProjectMapper;
import com.qoutraining.employeedirectory.repository.DepartmentRepository;
import com.qoutraining.employeedirectory.repository.EmployeePhoneRepository;
import com.qoutraining.employeedirectory.repository.EmployeeRepository;
import com.qoutraining.employeedirectory.service.EmployeeService;
import com.qoutraining.employeedirectory.service.JobTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobTitleService jobTitleService;
    private final DepartmentRepository departmentRepository;
    private final EmployeePhoneRepository employeePhoneRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeePhoneMapper employeePhoneMapper;
    private final EmployeeProjectMapper employeeProjectMapper;

    private Department findDepartmentByID(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: "+ id));
    }

    @Override
    public Employee findEmployeeByID(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<EmployeeResponseDTO> findAll() {
        return employeeMapper.toResponseListDto(employeeRepository.findAll());
    }

    @Override
    public EmployeeResponseDTO findById(Long id) {
        Employee employee= employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return employeeMapper.toResponseDto(employee);
    }

    @Override
    public List<EmployeePhoneResponseDTO> findEmployeePhoneById(Long id) {
        Employee employee=findEmployeeByID(id);
        return employeePhoneMapper.toRespnseListDto(employee.getEmployeePhones());
    }

    @Override
    public List<EmployeeProjectResponseDTO> findEmployeeProjectsById(Long id) {
        Employee employee=findEmployeeByID(id);
        List<EmployeeProject> projects=employee.getEmployeeProjects();
        return employeeProjectMapper.toProjectResponseList(projects);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO dto) {
        Department department=findDepartmentByID(dto.departmentId());
        JobTitle jobTitle=jobTitleService.findJobTitleById(dto.jobTitleId());

        Employee newEmployee=employeeMapper.toEntity(dto);

        newEmployee.setDepartment(department);
        newEmployee.setJobTitle(jobTitle);

        Employee savedEmployee=employeeRepository.save(newEmployee);

        return employeeMapper.toResponseDto(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeePhoneResponseDTO addEmployeePhone(EmployeePhoneRequestDTO dto) {
        Employee employee=findEmployeeByID(dto.employeeId());
        EmployeePhone employeePhone=employeePhoneMapper.toEntity(dto);
        employeePhone.setEmployee(employee);
         EmployeePhone savedEmployeePhone=employeePhoneRepository.save(employeePhone);
         return employeePhoneMapper.toResponseDto(savedEmployeePhone);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {
        Employee employee=findEmployeeByID(id);
        Department department=findDepartmentByID(dto.departmentId());
        JobTitle jobTitle=jobTitleService.findJobTitleById(dto.jobTitleId());
        employeeMapper.updateEntityFromDto(dto,employee);
        employee.setDepartment(department);
        employee.setJobTitle(jobTitle);
        Employee updateEmployee= employeeRepository.save(employee);
        return employeeMapper.toResponseDto(updateEmployee);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO terminateEmployee(Long id, LocalDate endDate) {
        Employee employee=findEmployeeByID(id);
        employee.setEndDate(endDate);
        Employee updateEmployee=employeeRepository.save(employee);
        return employeeMapper.toResponseDto(updateEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee=findEmployeeByID(id);
        employeeRepository.delete(employee);
    }
}
