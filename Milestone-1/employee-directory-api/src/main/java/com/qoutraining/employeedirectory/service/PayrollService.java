package com.qoutraining.employeedirectory.service;


import com.qoutraining.employeedirectory.model.dto.payroll.AddPayrollDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.ReadPayrollDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.UpdatePayrollDTO;

import java.time.LocalDate;
import java.util.List;

public interface PayrollService {
   List<ReadPayrollDTO> findAll();
   ReadPayrollDTO findById(Long id);
   ReadPayrollDTO addPayroll(AddPayrollDTO dto);
   ReadPayrollDTO updatePayroll(Long id, UpdatePayrollDTO dto);
   void deletePayroll(Long id);
}
