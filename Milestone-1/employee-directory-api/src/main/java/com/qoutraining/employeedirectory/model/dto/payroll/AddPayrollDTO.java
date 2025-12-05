package com.qoutraining.employeedirectory.model.dto.payroll;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AddPayrollDTO(
        Long employeeId,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate paymentDate,
        BigDecimal paidBaseSalary,
        BigDecimal bonusAmount,
        BigDecimal deductionAmount
) {
}
