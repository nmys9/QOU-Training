package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsDepartmentByName (String name);
}
