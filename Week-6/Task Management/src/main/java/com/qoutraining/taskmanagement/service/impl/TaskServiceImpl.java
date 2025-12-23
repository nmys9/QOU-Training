package com.qoutraining.taskmanagement.service.impl;

import com.qoutraining.taskmanagement.exception.TaskNotFoundException;
import com.qoutraining.taskmanagement.exception.UserNotFoundException;
import com.qoutraining.taskmanagement.model.dto.task.TaskRequestDto;
import com.qoutraining.taskmanagement.model.dto.task.TaskResponseDto;
import com.qoutraining.taskmanagement.model.entity.Task;
import com.qoutraining.taskmanagement.model.entity.User;
import com.qoutraining.taskmanagement.model.mapper.TaskMapper;
import com.qoutraining.taskmanagement.repository.TaskRepository;
import com.qoutraining.taskmanagement.repository.UserRepository;
import com.qoutraining.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public Page<TaskResponseDto> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable).map(taskMapper::toResponseDto);
    }

    @Override
    public TaskResponseDto findById(Long id) {
        Task task=taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task not found with id: "+ id));
        return taskMapper.toResponseDto(task);
    }

    @Override
    public Page<TaskResponseDto> findTasksByUserId(Long id, Pageable pageable) {
        User user=userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id :"+ id)
        );

        return taskRepository.findTasksByUserId(id,pageable).map(taskMapper::toResponseDto);
    }

    @Override
    public TaskResponseDto addTask(TaskRequestDto dto) {
        User user=userRepository.findById(dto.userId()).orElseThrow(
                () -> new UserNotFoundException("User not found with id :"+ dto.userId())
        );
        Task task=taskMapper.toEntity(dto);
        task.setUser(user);
        Task savedTask=taskRepository.save(task);
        return taskMapper.toResponseDto(savedTask);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto dto) {
        Task task=taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task not found with id: "+ id)
        );
        User user=userRepository.findById(dto.userId()).orElseThrow(
                () -> new UserNotFoundException("User not found with id :"+ dto.userId())
        );

        taskMapper.updateEntityFromDto(dto,task);

        task.setUser(user);

        Task updateTask=taskRepository.save(task);

        return taskMapper.toResponseDto(updateTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task task=taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task not found with id: "+ id)
        );
        taskRepository.delete(task);
    }
}
