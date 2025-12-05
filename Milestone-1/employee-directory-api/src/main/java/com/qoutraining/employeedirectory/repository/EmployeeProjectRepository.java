package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
}
