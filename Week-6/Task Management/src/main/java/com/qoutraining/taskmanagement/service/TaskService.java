package com.qoutraining.taskmanagement.service;

import com.qoutraining.taskmanagement.model.dto.task.TaskRequestDto;
import com.qoutraining.taskmanagement.model.dto.task.TaskResponseDto;
import java.util.List;

public interface TaskService {
    List<TaskResponseDto> findAll();
    TaskResponseDto findById(Long id);
    List<TaskResponseDto> findTasksByUserId(Long id);
    TaskResponseDto addTask(TaskRequestDto dto);
    TaskResponseDto updateTask(Long id,TaskRequestDto dto);
    void deleteTask(Long id);
}
