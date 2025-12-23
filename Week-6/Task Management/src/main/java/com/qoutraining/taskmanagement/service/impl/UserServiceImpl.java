package com.qoutraining.taskmanagement.service.impl;

import com.qoutraining.taskmanagement.exception.DuplicateResourceException;
import com.qoutraining.taskmanagement.exception.UserNotFoundException;
import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.model.dto.user.UserResponseDto;
import com.qoutraining.taskmanagement.model.entity.User;
import com.qoutraining.taskmanagement.model.mapper.UserMapper;
import com.qoutraining.taskmanagement.repository.TaskRepository;
import com.qoutraining.taskmanagement.repository.UserRepository;
import com.qoutraining.taskmanagement.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserResponseDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponseDto);
    }

    @Override
    public UserResponseDto findById(Long id) {
        User user=userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id: "+ id)
        );
        return userMapper.toResponseDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto addUser(UserRequestDto dto) {
        if(userRepository.existsByEmail(dto.email())){
            throw new DuplicateResourceException("User with email: " + dto.email() + " already exists.");
        }
        User user=userMapper.toEntity(dto);
        User savedUser=userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user=userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id :"+ id)
        );
        if( !user.getEmail().equals(dto.email()) && userRepository.existsByEmail(dto.email()) ){
            throw new DuplicateResourceException("User with email: " + dto.email() + " already exists.");
        }
        userMapper.updateEntityFromDto(dto,user);
        User updateUser=userRepository.save(user);

        return userMapper.toResponseDto(updateUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user=userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id :"+ id)
        );
        taskRepository.deleteAll(user.getTasks());
        userRepository.delete(user);
    }
}
