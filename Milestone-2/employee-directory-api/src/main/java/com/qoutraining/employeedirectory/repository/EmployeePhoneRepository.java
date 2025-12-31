package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneResponseDTO;
import com.qoutraining.employeedirectory.model.entity.EmployeePhone;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeePhoneRepository extends JpaRepository<EmployeePhone, Long> {
    Page<EmployeePhone> findEmployeePhoneByEmployee_Id (Long id, Pageable pageable);

    @Modifying
    @Query("DELETE EmployeePhone e WHERE e.employee.id= :id")
    void deleteEmployeePhoneByEmployeeId (@Param("id") Long id);
}
