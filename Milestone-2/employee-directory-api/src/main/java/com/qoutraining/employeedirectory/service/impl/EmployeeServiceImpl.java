package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.InvalidEndDateException;
import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employee.UpdateEmployeeDTO;
import com.qoutraining.employeedirectory.model.entity.*;
import com.qoutraining.employeedirectory.model.mapper.EmployeeMapper;
import com.qoutraining.employeedirectory.model.mapper.EmployeePhoneMapper;
import com.qoutraining.employeedirectory.model.mapper.EmployeeProjectMapper;
import com.qoutraining.employeedirectory.repository.*;
import com.qoutraining.employeedirectory.service.EmployeeService;
import com.qoutraining.employeedirectory.service.JobTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobTitleService jobTitleService;
    private final DepartmentRepository departmentRepository;
    private final EmployeePhoneRepository employeePhoneRepository;
    private final EmployeeProjectRepository employeeProjectRepository;
    private final PayrollRepository payrollRepository;
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
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public Page<EmployeeResponseDTO> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(employeeMapper::toResponseDto);
    }

    @Override
    public EmployeeResponseDTO findById(Long id) {
        Employee employee= employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return employeeMapper.toResponseDto(employee);
    }

    @Override
    public EmployeeResponseDTO getMyProfile(UserDetails userDetails) {
        String email=userDetails.getUsername();
        Employee employee=employeeRepository.findByUserEmail(email).orElseThrow(() -> new UsernameNotFoundException("Employee not found"));
        return employeeMapper.toResponseDto(employee);
    }

    @Override
    public Page<EmployeePhoneResponseDTO> findEmployeePhone(UserDetails userDetails,Pageable pageable) {
        String email= userDetails.getUsername();
        return employeePhoneRepository.findEmployeePhoneByEmployee_User_Email(email,pageable)
                .map(employeePhoneMapper::toResponseDto);
    }

    @Override
    public Page<EmployeeProjectResponseDTO> findEmployeeProjects(UserDetails userDetails,Pageable pageable) {
        String email= userDetails.getUsername();
        return employeeProjectRepository.findEmployeeProjectByEmployee_User_Email(email,pageable)
                .map(employeeProjectMapper::toProjectResponseDto);
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
    public EmployeeResponseDTO updateEmployee(UserDetails userDetails, UpdateEmployeeDTO update) {
        String email= userDetails.getUsername();
        Employee employee=employeeRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found."));
        if(!(update.departmentId()==null)){
            Department department=findDepartmentByID(update.departmentId());
            employee.setDepartment(department);
        }
        if (!(update.jobTitleId() == null)) {
            JobTitle jobTitle=jobTitleService.findJobTitleById(update.jobTitleId());
            employee.setJobTitle(jobTitle);
        }
        employeeMapper.updateEntityFromDto(update,employee);
        Employee updateEmployee= employeeRepository.save(employee);
        return employeeMapper.toResponseDto(updateEmployee);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO terminateEmployee(Long id, LocalDate endDate) {
        Employee employee=findEmployeeByID(id);
        if(employee.getEndDate() != null){
            throw new InvalidEndDateException("Employee is already terminated. Cannot modify End Date.");
        }
        if(endDate.isBefore(employee.getHireDate()) || endDate.isEqual(employee.getHireDate())){
            throw new InvalidEndDateException("End Date (" + endDate + ") must be after Hire Date (" + employee.getHireDate() + ")");
        }
        employee.setEndDate(endDate);
        Employee updateEmployee=employeeRepository.save(employee);
        return employeeMapper.toResponseDto(updateEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee=findEmployeeByID(id);
        employeePhoneRepository.deleteEmployeePhoneByEmployeeId(id);
        employeeProjectRepository.detachEmployeeProjectByEmployeeId(id);
        payrollRepository.deletePayrollsByEmployeeId(id);
        employeeRepository.delete(employee);
    }
}
