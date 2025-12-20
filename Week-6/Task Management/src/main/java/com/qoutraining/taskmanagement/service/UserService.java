package com.qoutraining.taskmanagement.service;

import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.model.dto.user.UserResponseDto;
import java.util.List;

public interface UserService {
    List<UserResponseDto> findAll();
    UserResponseDto findById(Long id);
    UserResponseDto addUser(UserRequestDto dto);
    UserResponseDto updateUser(Long id,UserRequestDto dto);
    void deleteUser(Long id);
}
