package com.qoutraining.taskmanagement.service;

import com.qoutraining.taskmanagement.model.dto.task.TaskRequestDto;
import com.qoutraining.taskmanagement.model.dto.task.TaskResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Page<TaskResponseDto> findAll(Pageable pageable);
    TaskResponseDto findById(Long id);
    Page<TaskResponseDto> findTasksByUserId(Long id, Pageable pageable);
    TaskResponseDto addTask(TaskRequestDto dto);
    TaskResponseDto updateTask(Long id,TaskRequestDto dto);
    void deleteTask(Long id);
}
