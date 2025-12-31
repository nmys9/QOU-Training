package com.qoutraining.employeedirectory.model.dto.payroll;


import java.math.BigDecimal;
import java.time.LocalDate;

public record PayrollResponseDTO(
        Long id,
        Long employeeId,
        LocalDate paymentDate,
        BigDecimal paidBaseSalary,
        BigDecimal bonusAmount,
        BigDecimal deductionAmount,
        BigDecimal netPay
) {
}
