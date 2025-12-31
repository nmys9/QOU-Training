package com.qoutraining.taskmanagement.controller;

import com.qoutraining.taskmanagement.model.dto.task.TaskRequestDto;
import com.qoutraining.taskmanagement.model.dto.task.TaskResponseDto;
import com.qoutraining.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.OptionalLong;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskConroller {

    private final TaskService taskService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<TaskResponseDto>> findAllTasks(
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ){
        return ResponseEntity.ok(taskService.findAll(pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findTaskById(Long id){
        var response = taskService.findById(id);
        return ResponseEntity.ok(response);
    }




}
