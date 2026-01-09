package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.employee.EmployeeProjectResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneRequestDTO;
import com.qoutraining.employeedirectory.model.dto.employee.EmployeeResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employeePhone.EmployeePhoneResponseDTO;
import com.qoutraining.employeedirectory.model.dto.employee.UpdateEmployeeDTO;
import com.qoutraining.employeedirectory.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;




    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id){
        var response= employeeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    public ResponseEntity<EmployeeResponseDTO> getMyProfile(
            @AuthenticationPrincipal UserDetails userDetails){
        var response=employeeService.getMyProfile(userDetails);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me/phones")
    public ResponseEntity<Page<EmployeePhoneResponseDTO>> getEmployeePhones(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        var response= employeeService.findEmployeePhone(userDetails,pageable);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me/projects")
    public ResponseEntity<Page<EmployeeProjectResponseDTO>> getEmployeeProject(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        var response= employeeService.findEmployeeProjects(userDetails,pageable);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/phones")
    public ResponseEntity<EmployeePhoneResponseDTO> addEmployeePhone(@Valid @RequestBody EmployeePhoneRequestDTO dto){
        var response= employeeService.addEmployeePhone(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/me")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UpdateEmployeeDTO update){
        var response = employeeService.updateEmployee(userDetails, update);
        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/terminate/{id}")
    public ResponseEntity<EmployeeResponseDTO> terminateEmployee(@PathVariable Long id,@RequestBody LocalDate date){
        var response = employeeService.terminateEmployee(id, date);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
