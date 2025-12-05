package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.payroll.AddPayrollDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.ReadPayrollDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.UpdatePayrollDTO;
import com.qoutraining.employeedirectory.model.entity.Employee;
import com.qoutraining.employeedirectory.model.entity.Payroll;
import com.qoutraining.employeedirectory.repository.EmployeeRepository;
import com.qoutraining.employeedirectory.repository.PayrollRepository;
import com.qoutraining.employeedirectory.service.PayrollService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;
    private final EntityManager entityManager;

    @Override
    public List<ReadPayrollDTO> findAll() {
        return payrollRepository.findAll().stream()
                .map(ReadPayrollDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public ReadPayrollDTO findById(Long id) {
        Payroll payroll=existsPayroll(id);
        return ReadPayrollDTO.fromEntity(payroll);
    }

    @Override
    @Transactional
    public ReadPayrollDTO addPayroll(AddPayrollDTO dto) {
        Employee employee=employeeRepository.findById(dto.employeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + dto.employeeId()));

        Payroll newPayroll=Payroll.builder()
                .employee(employee)
                .paymentDate(dto.paymentDate())
                .paidBaseSalary(dto.paidBaseSalary())
                .bonusAmount(dto.bonusAmount())
                .deductionAmount(dto.deductionAmount())
                .build();

        newPayroll=payrollRepository.save(newPayroll);
        entityManager.refresh(newPayroll);

        return ReadPayrollDTO.fromEntity(newPayroll);
    }

    @Override
    @Transactional
    public ReadPayrollDTO updatePayroll(Long id, UpdatePayrollDTO dto) {
        Payroll payroll=existsPayroll(id);

        payroll.setPaidBaseSalary(dto.paidBaseSalary());
        payroll.setBonusAmount(dto.bonusAmount());
        payroll.setDeductionAmount(dto.deductionAmount());

        Payroll updatePayroll=payrollRepository.save(payroll);
        entityManager.refresh(updatePayroll);

        return ReadPayrollDTO.fromEntity(updatePayroll);
    }

    @Override
    @Transactional
    public void deletePayroll(Long id) {
       Payroll payroll=existsPayroll(id);
       payrollRepository.delete(payroll);
    }

    public Payroll existsPayroll(Long id){
        return payrollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payrollcannot found with id :" + id));
    }
}
