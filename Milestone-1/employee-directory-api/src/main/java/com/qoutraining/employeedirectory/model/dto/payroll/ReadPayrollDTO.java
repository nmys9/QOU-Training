package com.qoutraining.employeedirectory.model.dto.payroll;

import com.qoutraining.employeedirectory.model.entity.Payroll;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReadPayrollDTO(
        Long id,
        Long employeeId,
        LocalDate paymentDate,
        BigDecimal paidBaseSalary,
        BigDecimal bonusAmount,
        BigDecimal deductionAmount,
        BigDecimal netPay
) {
    public static ReadPayrollDTO fromEntity(Payroll payroll){
        return new ReadPayrollDTO(
                payroll.getId(),
                payroll.getEmployee().getId(),
                payroll.getPaymentDate(),
                payroll.getPaidBaseSalary(),
                payroll.getBonusAmount(),
                payroll.getDeductionAmount(),
                payroll.getNetPay()
        );
    }
}
