package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE Employee e SET e.department=null WHERE e.department.id= :deptId")
    void detachEmployeesFromDepartment(@Param("deptId") Long deptId);

    @Modifying
    @Query("UPDATE Employee e SET e.jobTitle=null WHERE e.jobTitle.id= :jobId")
    void detachEmployeesFromJobTitle(@Param("jobId") Long jobId);

}
