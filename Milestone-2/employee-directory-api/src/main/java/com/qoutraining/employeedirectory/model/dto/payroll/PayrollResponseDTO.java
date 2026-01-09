package com.qoutraining.employeedirectory.model.dto.payroll;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PayrollResponseDTO(
        Long id,
        Long employeeId,
        LocalDate paymentDate,
        BigDecimal paidBaseSalary,
        BigDecimal bonusAmount,
        BigDecimal deductionAmount,
        BigDecimal netPay,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate,
        String createdBy,
        String lastModifiedBy
) {
}
