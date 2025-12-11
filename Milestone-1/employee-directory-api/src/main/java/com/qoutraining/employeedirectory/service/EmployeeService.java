package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneResponseDTO;
import com.qoutraining.employeedirectory.model.entity.Employee;
import com.qoutraining.employeedirectory.model.entity.EmployeePhone;

import java.time.LocalDate;
import java.util.List;


public interface EmployeeService {
    List<EmployeeResponseDTO> findAll();
    EmployeeResponseDTO findById(Long id);
    Employee findEmployeeByID(Long id);
    List<EmployeePhoneResponseDTO> findEmployeePhoneById(Long id);
    List<EmployeeProjectResponseDTO> findEmployeeProjectsById(Long id);
    EmployeeResponseDTO addEmployee(EmployeeRequestDTO dto);
    EmployeePhoneResponseDTO addEmployeePhone(EmployeePhoneRequestDTO dto);
    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto);
    EmployeeResponseDTO terminateEmployee(Long id, LocalDate endDate);
    void deleteEmployee(Long id);
}
