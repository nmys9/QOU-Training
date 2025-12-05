package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.department.AddDepartmentDTO;
import com.qoutraining.employeedirectory.model.dto.department.ReadDepartmentDTO;
import com.qoutraining.employeedirectory.service.DepartmentService;
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
    public List<ReadDepartmentDTO> getDepartments(){
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public ReadDepartmentDTO getDepartment(@PathVariable Long id){
        return departmentService.findById(id);
    }

    @PostMapping
    public ReadDepartmentDTO addDepartment(@RequestBody AddDepartmentDTO department){
        return departmentService.addDepartment(department);
    }

    @PutMapping("/{id}")
    public ReadDepartmentDTO updateDepartment(@PathVariable Long id,
                                              @RequestBody AddDepartmentDTO update){
        return departmentService.updateDepartment(id,update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

}
