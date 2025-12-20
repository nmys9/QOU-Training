package com.qoutraining.taskmanagement.service.impl;

import com.qoutraining.taskmanagement.exception.UserNotFoundException;
import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.model.dto.user.UserResponseDto;
import com.qoutraining.taskmanagement.model.entity.User;
import com.qoutraining.taskmanagement.model.mapper.UserMapper;
import com.qoutraining.taskmanagement.repository.UserRepository;
import com.qoutraining.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> findAll() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    @Override
    public UserResponseDto findById(Long id) {
        User user=userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id: "+ id)
        );
        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto addUser(UserRequestDto dto) {
        User user=userMapper.toEntity(dto);
        User savedUser=userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user=userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id :"+ id)
        );
        userMapper.updateEntityFromDto(dto,user);
        User updateUser=userRepository.save(user);

        return userMapper.toResponseDto(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user=userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id :"+ id)
        );
        userRepository.delete(user);
    }
}
