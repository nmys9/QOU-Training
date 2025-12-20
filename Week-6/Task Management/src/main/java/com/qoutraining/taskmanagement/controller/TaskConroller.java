package com.qoutraining.taskmanagement.controller;

import com.qoutraining.taskmanagement.model.dto.task.TaskRequestDto;
import com.qoutraining.taskmanagement.model.dto.task.TaskResponseDto;
import com.qoutraining.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.OptionalLong;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskConroller {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> findAll(){
        var response= taskService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findById(Long id){
        var response = taskService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody TaskRequestDto dto){
        var response = taskService.addTask(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto){
        var response = taskService.updateTask(id,dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
