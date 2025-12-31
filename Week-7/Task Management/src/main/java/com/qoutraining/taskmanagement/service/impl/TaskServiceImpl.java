package com.qoutraining.taskmanagement.service.impl;

import com.qoutraining.taskmanagement.exception.TaskNotFoundException;
import com.qoutraining.taskmanagement.exception.TaskOwnershipException;
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
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
    public Page<TaskResponseDto> findTasksByUserEmail(String email, Pageable pageable) {
        User user=userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User not found.")
        );
        return taskRepository.findTasksByUserEmail(email,pageable).map(taskMapper::toResponseDto);
    }

    @Override
    @Transactional
    public TaskResponseDto addTask(String email,TaskRequestDto dto) {
        User user=userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User not found.")
        );
        Task task=taskMapper.toEntity(dto);
        task.setUser(user);
        Task savedTask=taskRepository.save(task);
        return taskMapper.toResponseDto(savedTask);
    }

    @Override
    @Transactional
    public TaskResponseDto updateTask(String email,Long taskId, TaskRequestDto dto) {
        Task task=taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException("Task not found with id: "+ taskId)
        );
        User user=userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User not found.")
        );

        //Ensure task's user is not changed
        if(!task.getUser().getId().equals(user.getId())){
            throw new TaskOwnershipException("You cannot change the user of this task.");
        }
        taskMapper.updateEntityFromDto(dto,task);
        Task updateTask=taskRepository.save(task);
        return taskMapper.toResponseDto(updateTask);
    }

    @Override
    @Transactional
    public void deleteTask(String email,Long taskId) {
        Task task=taskRepository.findByIdAndUserEmail(taskId, email).orElseThrow(
                () -> new TaskOwnershipException("Task not found or you don't have permission to delete it.")
        );
        taskRepository.delete(task);
    }
}
