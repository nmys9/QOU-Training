package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.department.DepartmentRequestDTO;
import com.qoutraining.employeedirectory.model.dto.department.DepartmentResponseDTO;
import com.qoutraining.employeedirectory.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentResponseDTO> getDepartments(){
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public DepartmentResponseDTO getDepartment(@PathVariable Long id){
        return departmentService.findById(id);
    }

    @PostMapping
    public DepartmentResponseDTO addDepartment(@Valid @RequestBody DepartmentRequestDTO department){
        return departmentService.addDepartment(department);
    }

    @PutMapping("/{id}")
    public DepartmentResponseDTO updateDepartment(@PathVariable Long id,
                                                  @Valid @RequestBody DepartmentRequestDTO update){
        return departmentService.updateDepartment(id,update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

}
