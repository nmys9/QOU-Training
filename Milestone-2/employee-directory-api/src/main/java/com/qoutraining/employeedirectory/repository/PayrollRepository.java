package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    @Modifying
    @Query("DELETE Payroll p WHERE p.employee.id= :id")
    void deletePayrollsByEmployeeId(@Param("id") Long id);
}
