package com.qoutraining.taskmanagement.service;

import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.model.dto.user.UserResponseDto;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {
    Page<UserResponseDto> findAll(Pageable pageable);
    UserResponseDto findById(Long id);
    UserResponseDto addUser(UserRequestDto dto);
    UserResponseDto updateUser(Long id,UserRequestDto dto);
    void deleteUser(Long id);
}
