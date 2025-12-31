package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneResponseDTO;
import com.qoutraining.employeedirectory.model.entity.EmployeePhone;
import com.qoutraining.employeedirectory.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDTO>> getEmployees(
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ){
        var response= employeeService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id){
        var response= employeeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/phones")
    public ResponseEntity<Page<EmployeePhoneResponseDTO>> getEmployeePhones(
            @PathVariable Long id,
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        var response= employeeService.findEmployeePhoneById(id,pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<Page<EmployeeProjectResponseDTO>> getEmployeeProject(
            @PathVariable Long id,
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        var response= employeeService.findEmployeeProjectsById(id,pageable);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> addEmployee(
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){
        var response=employeeService.addEmployee(employeeRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/phones")
    public ResponseEntity<EmployeePhoneResponseDTO> addEmployeePhone(@Valid @RequestBody EmployeePhoneRequestDTO dto){
        var response= employeeService.addEmployeePhone(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id,
                                              @Valid @RequestBody EmployeeRequestDTO updateEmployeeDTO){
        var response = employeeService.updateEmployee(id, updateEmployeeDTO);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> terminateEmployee(@PathVariable Long id,@RequestBody LocalDate date){
        var response = employeeService.terminateEmployee(id, date);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
