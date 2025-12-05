package com.qoutraining.employeedirectory.model.dto.payroll;

import java.math.BigDecimal;

public record UpdatePayrollDTO(
        BigDecimal paidBaseSalary,
        BigDecimal bonusAmount,
        BigDecimal deductionAmount
) {
}
