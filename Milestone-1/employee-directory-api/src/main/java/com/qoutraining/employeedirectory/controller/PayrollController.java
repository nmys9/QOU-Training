package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.payroll.PayrollRequestDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.PayrollResponseDTO;
import com.qoutraining.employeedirectory.service.PayrollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("payroll")
public class PayrollController {

    private final PayrollService payrollService;

    @GetMapping
    public List<PayrollResponseDTO> getPayroll(){
        return payrollService.findAll();
    }

    @GetMapping("/{id}")
    public PayrollResponseDTO getPayrollById(@PathVariable Long id){
        return payrollService.findById(id);
    }

    @PostMapping
    public PayrollResponseDTO addPayroll(@Valid @RequestBody PayrollRequestDTO dto){
        return payrollService.addPayroll(dto);
    }

    @PutMapping("/{id}")
    public PayrollResponseDTO updatePayroll(@PathVariable Long id,
                                            @Valid @RequestBody PayrollRequestDTO update){
        return payrollService.updatePayroll(id,update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id){
        payrollService.deletePayroll(id);
        return ResponseEntity.noContent().build();
    }

}
