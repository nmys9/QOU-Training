package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
