package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
    Page<EmployeeProject> findEmployeeProjectByEmployee_User_Email(String email, Pageable pageable);

    Page<EmployeeProject> findEmployeeProjectByProject_Id (Long id,Pageable pageable);

    @Modifying
    @Query("UPDATE EmployeeProject e SET e.employee=null WHERE e.employee.id= :empId")
    void detachEmployeeProjectByEmployeeId(@Param("empId")Long empId);

    @Modifying
    @Query("UPDATE EmployeeProject e SET e.project=null WHERE e.project.id= :proId")
    void detachEmployeeProjectByProjectId(@Param("proId") Long proId);
}
