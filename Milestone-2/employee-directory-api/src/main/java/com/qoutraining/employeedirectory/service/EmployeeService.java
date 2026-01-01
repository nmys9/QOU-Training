package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employee.UpdateEmployeeDTO;
import com.qoutraining.employeedirectory.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;


public interface EmployeeService {
    Page<EmployeeResponseDTO> findAll(Pageable pageable);
    EmployeeResponseDTO findById(Long id);
    EmployeeResponseDTO getMyProfile(UserDetails userDetails);
    Employee findEmployeeByID(Long id);
    Page<EmployeePhoneResponseDTO> findEmployeePhone(UserDetails userDetails,Pageable pageable);
    Page<EmployeeProjectResponseDTO> findEmployeeProjects(UserDetails userDetails,Pageable pageable);
    EmployeePhoneResponseDTO addEmployeePhone(EmployeePhoneRequestDTO dto);
    EmployeeResponseDTO updateEmployee(UserDetails userDetails, UpdateEmployeeDTO update);
    EmployeeResponseDTO terminateEmployee(Long id, LocalDate endDate);
    void deleteEmployee(Long id);
}
