package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectDTO;
import com.qoutraining.employeedirectory.model.dto.employee.AddEmployeeDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneDTO;
import com.qoutraining.employeedirectory.model.dto.employee.ReadEmployeeDTO;
import com.qoutraining.employeedirectory.model.dto.employee.UpdateEmployeeDTO;
import com.qoutraining.employeedirectory.model.entity.EmployeePhone;

import java.time.LocalDate;
import java.util.List;


public interface EmployeeService {
    List<ReadEmployeeDTO> findAll();
    ReadEmployeeDTO findByID(Long id);
    List<EmployeePhone> findEmployeePhoneById(Long id);
    List<EmployeeProjectDTO> findEmployeeProjectsById(Long id);
    ReadEmployeeDTO addEmployee(AddEmployeeDTO dto);
    EmployeePhone addEmployeePhone(Long id, EmployeePhoneDTO dto);
    ReadEmployeeDTO updateEmployee(Long id, UpdateEmployeeDTO dto);
    void terminateEmployee(Long id, LocalDate endDate);
}
