package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.entity.EmployeePhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePhoneRepository extends JpaRepository<EmployeePhone, Long> {
}
