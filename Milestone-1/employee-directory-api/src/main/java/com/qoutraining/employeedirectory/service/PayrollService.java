package com.qoutraining.employeedirectory.service;


import com.qoutraining.employeedirectory.model.dto.payroll.PayrollRequestDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.PayrollResponseDTO;

import java.util.List;

public interface PayrollService {
   List<PayrollResponseDTO> findAll();
   PayrollResponseDTO findById(Long id);
   PayrollResponseDTO addPayroll(PayrollRequestDTO dto);
   PayrollResponseDTO updatePayroll(Long id, PayrollRequestDTO dto);
   void deletePayroll(Long id);
}
