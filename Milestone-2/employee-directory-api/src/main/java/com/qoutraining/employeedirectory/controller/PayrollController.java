package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.payroll.PayrollRequestDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.PayrollResponseDTO;
import com.qoutraining.employeedirectory.service.PayrollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("payroll")
public class PayrollController {

    private final PayrollService payrollService;

    @GetMapping
    public ResponseEntity<Page<PayrollResponseDTO>> findAllPayroll(
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC)Pageable pageable
            ){
        var response= payrollService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayrollResponseDTO> getPayrollById(@PathVariable Long id){
        var response= payrollService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PayrollResponseDTO> addPayroll(@Valid @RequestBody PayrollRequestDTO dto){
        var response= payrollService.addPayroll(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PayrollResponseDTO> updatePayroll(@PathVariable Long id,
                                            @Valid @RequestBody PayrollRequestDTO update){
        var response= payrollService.updatePayroll(id,update);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id){
        payrollService.deletePayroll(id);
        return ResponseEntity.noContent().build();
    }

}
