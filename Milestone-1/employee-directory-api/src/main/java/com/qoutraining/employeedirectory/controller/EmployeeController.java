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
    public List<EmployeeResponseDTO> getEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployee(@PathVariable Long id){
        return employeeService.findById(id);
    }

    @GetMapping("/{id}/phones")
    public List<EmployeePhoneResponseDTO> getEmployeePhones(@PathVariable Long id){
        return employeeService.findEmployeePhoneById(id);
    }

    @GetMapping("/{id}/projects")
    public List<EmployeeProjectResponseDTO> getEmployeeProject(@PathVariable Long id){
        return employeeService.findEmployeeProjectsById(id);
    }


    @PostMapping
    public EmployeeResponseDTO addEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){
        return employeeService.addEmployee(employeeRequestDTO);
    }

    @PostMapping("/phones")
    public EmployeePhoneResponseDTO addEmployeePhone(@Valid @RequestBody EmployeePhoneRequestDTO dto){
        return employeeService.addEmployeePhone(dto);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Long id,
                                              @Valid @RequestBody EmployeeRequestDTO updateEmployeeDTO){
        return employeeService.updateEmployee(id, updateEmployeeDTO);
    }


    @PatchMapping("/{id}")
    public EmployeeResponseDTO terminateEmployee(@PathVariable Long id,@RequestBody LocalDate date){
        return employeeService.terminateEmployee(id, date);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);

        return ResponseEntity.ok("Employee with ID " + id + " has been deleted.");
    }

}
