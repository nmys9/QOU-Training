package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.payroll.PayrollRequestDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.PayrollResponseDTO;
import com.qoutraining.employeedirectory.model.entity.Employee;
import com.qoutraining.employeedirectory.model.entity.Payroll;
import com.qoutraining.employeedirectory.model.mapper.PayrollMapper;
import org.springframework.transaction.annotation.Transactional;
import com.qoutraining.employeedirectory.repository.PayrollRepository;
import com.qoutraining.employeedirectory.service.EmployeeService;
import com.qoutraining.employeedirectory.service.PayrollService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeService employeeService;
    private final EntityManager entityManager;
    private final PayrollMapper payrollMapper;

    private Payroll findPayrollById(Long id) {
        return payrollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll cannot found with id :" + id));
    }

    @Override
    public List<PayrollResponseDTO> findAll() {
        return payrollMapper.toResponseListDto(payrollRepository.findAll());
    }

    @Override
    public PayrollResponseDTO findById(Long id) {
        Payroll payroll=findPayrollById(id);
        return payrollMapper.toResponseDto(payroll);
    }

    @Override
    @Transactional
    public PayrollResponseDTO addPayroll(PayrollRequestDTO dto) {
        Employee employee=employeeService.findEmployeeByID(dto.employeeId());
        Payroll newPayroll=payrollMapper.toEntity(dto);
        newPayroll.setEmployee(employee);
        Payroll savedPayroll=payrollRepository.save(newPayroll);
        entityManager.refresh(savedPayroll);
        return payrollMapper.toResponseDto(savedPayroll);
    }

    @Override
    @Transactional
    public PayrollResponseDTO updatePayroll(Long id, PayrollRequestDTO dto) {
        Payroll payroll=findPayrollById(id);
        Employee employee=employeeService.findEmployeeByID(dto.employeeId());
        payrollMapper.updateEntityFromDto(dto,payroll);
        payroll.setEmployee(employee);
        Payroll updatePayroll=payrollRepository.saveAndFlush(payroll);
        entityManager.refresh(updatePayroll);
        return payrollMapper.toResponseDto(updatePayroll);
    }

    @Override
    @Transactional
    public void deletePayroll(Long id) {
       Payroll payroll=findPayrollById(id);
       payrollRepository.delete(payroll);
    }
}
