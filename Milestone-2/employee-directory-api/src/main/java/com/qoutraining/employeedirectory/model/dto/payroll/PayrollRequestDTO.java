package com.qoutraining.employeedirectory.model.dto.payroll;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PayrollRequestDTO(

        @NotNull(message = "Employee ID is required.")
        @Positive(message = "Employee ID must be a positive number.")
        Long employeeId,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "Payment date is required.")
        @PastOrPresent(message = "Payment date cannot be in the future.")
        LocalDate paymentDate,

        @NotNull(message = "Base salary is mandatory.")
        @PositiveOrZero(message = "Base salary must be zero or positive.")
        BigDecimal paidBaseSalary,

        @PositiveOrZero(message = "Bonus amount must be zero or positive.")
        BigDecimal bonusAmount,

        @PositiveOrZero(message = "Deduction amount must be zero or positive.")
        BigDecimal deductionAmount
) {
}
