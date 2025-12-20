package com.qoutraining.taskmanagement.service;

import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.model.dto.user.UserResponseDto;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {
    Page<UserResponseDto> findAll(int page, int size, String sortBy);
    UserResponseDto findById(Long id);
    UserResponseDto addUser(UserRequestDto dto);
    UserResponseDto updateUser(Long id,UserRequestDto dto);
    void deleteUser(Long id);
}
