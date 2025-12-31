package com.qoutraining.employeedirectory.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYROLL")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    private Employee employee;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "PAYMENT_DATE", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "PAID_BASE_SALARY", precision = 7, scale = 2,nullable = false)
    private BigDecimal paidBaseSalary;

    @Column(name = "BONUS_AMOUNT", precision = 7, scale = 2)
    private BigDecimal bonusAmount;

    @Column(name = "DEDUCTION_AMOUNT", precision = 7, scale = 2)
    private BigDecimal deductionAmount;

    @Column(name = "NET_PAY", precision = 7, scale = 2, insertable = false, updatable = false)
    private BigDecimal netPay;
}
