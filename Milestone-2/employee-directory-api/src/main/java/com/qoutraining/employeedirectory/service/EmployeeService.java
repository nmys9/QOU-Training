package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneResponseDTO;
import com.qoutraining.employeedirectory.model.entity.Employee;
import com.qoutraining.employeedirectory.model.entity.EmployeePhone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;


public interface EmployeeService {
    Page<EmployeeResponseDTO> findAll(Pageable pageable);
    EmployeeResponseDTO findById(Long id);
    Employee findEmployeeByID(Long id);
    Page<EmployeePhoneResponseDTO> findEmployeePhoneById(Long id,Pageable pageable);
    Page<EmployeeProjectResponseDTO> findEmployeeProjectsById(Long id,Pageable pageable);
    EmployeeResponseDTO addEmployee(EmployeeRequestDTO dto);
    EmployeePhoneResponseDTO addEmployeePhone(EmployeePhoneRequestDTO dto);
    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto);
    EmployeeResponseDTO terminateEmployee(Long id, LocalDate endDate);
    void deleteEmployee(Long id);
}
