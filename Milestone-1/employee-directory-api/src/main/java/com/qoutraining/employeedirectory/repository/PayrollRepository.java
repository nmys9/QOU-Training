package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
}
