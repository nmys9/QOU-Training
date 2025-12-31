package com.qoutraining.taskmanagement.service;

import com.qoutraining.taskmanagement.model.dto.task.TaskRequestDto;
import com.qoutraining.taskmanagement.model.dto.task.TaskResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Page<TaskResponseDto> findAll(Pageable pageable);
    TaskResponseDto findById(Long id);
    Page<TaskResponseDto> findTasksByUserEmail(String email, Pageable pageable);
    TaskResponseDto addTask(String email,TaskRequestDto dto);
    TaskResponseDto updateTask(String email,Long taskId,TaskRequestDto dto);
    void deleteTask(String email,Long taskId);
}
