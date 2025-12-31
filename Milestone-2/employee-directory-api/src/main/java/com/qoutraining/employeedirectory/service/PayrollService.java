package com.qoutraining.employeedirectory.service;


import com.qoutraining.employeedirectory.model.dto.payroll.PayrollRequestDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.PayrollResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PayrollService {
   Page<PayrollResponseDTO> findAll(Pageable pageable);
   PayrollResponseDTO findById(Long id);
   PayrollResponseDTO addPayroll(PayrollRequestDTO dto);
   PayrollResponseDTO updatePayroll(Long id, PayrollRequestDTO dto);
   void deletePayroll(Long id);
}
