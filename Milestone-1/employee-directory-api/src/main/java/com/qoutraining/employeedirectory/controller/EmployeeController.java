package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectDTO;
import com.qoutraining.employeedirectory.model.dto.employee.AddEmployeeDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneDTO;
import com.qoutraining.employeedirectory.model.dto.employee.ReadEmployeeDTO;
import com.qoutraining.employeedirectory.model.dto.employee.UpdateEmployeeDTO;
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
    public List<ReadEmployeeDTO> getEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public ReadEmployeeDTO getEmployee(@PathVariable Long id){
        return employeeService.findByID(id);
    }

    @GetMapping("/{id}/phones")
    public List<EmployeePhone> getEmployeePhones(@PathVariable Long id){
        return employeeService.findEmployeePhoneById(id);
    }

    @GetMapping("/{id}/projects")
    public List<EmployeeProjectDTO> getEmployeeProject(@PathVariable Long id){
        return employeeService.findEmployeeProjectsById(id);
    }

    @PostMapping
    public ReadEmployeeDTO addEmployee(@Valid @RequestBody AddEmployeeDTO addEmployeeDTO){
        return employeeService.addEmployee(addEmployeeDTO);
    }

    @PostMapping("/{id}/phones")
    public EmployeePhone addEmployeePhone(@PathVariable Long id,
                                          @RequestBody EmployeePhoneDTO dto){
        return employeeService.addEmployeePhone(id,dto);
    }

    @PutMapping("/{id}")
    public ReadEmployeeDTO updateEmployee(@PathVariable Long id,
                                          @RequestBody UpdateEmployeeDTO updateEmployeeDTO){
        return employeeService.updateEmployee(id, updateEmployeeDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> terminateEmployee(@PathVariable Long id){
        employeeService.terminateEmployee(id, LocalDate.now());

        return ResponseEntity.ok("Employee with ID " + id + " has been terminated (Soft Delete)");
    }

}
