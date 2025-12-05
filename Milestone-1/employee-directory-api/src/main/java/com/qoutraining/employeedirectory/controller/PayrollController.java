package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.payroll.AddPayrollDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.ReadPayrollDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.UpdatePayrollDTO;
import com.qoutraining.employeedirectory.service.PayrollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("payroll")
public class PayrollController {

    private final PayrollService payrollService;

    @GetMapping
    public List<ReadPayrollDTO> getPayroll(){
        return payrollService.findAll();
    }

    @GetMapping("/{id}")
    public ReadPayrollDTO getPayrollById(@PathVariable Long id){
        return payrollService.findById(id);
    }

    @PostMapping
    public ReadPayrollDTO addPayroll(@Valid @RequestBody AddPayrollDTO dto){
        return payrollService.addPayroll(dto);
    }

    @PutMapping("/{id}")
    public ReadPayrollDTO updatePayroll(@PathVariable Long id,
                                          @RequestBody UpdatePayrollDTO update){
        return payrollService.updatePayroll(id,update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id){
        payrollService.deletePayroll(id);
        return ResponseEntity.noContent().build();
    }

}
