package com.qoutraining.taskmanagement.service;

import com.qoutraining.taskmanagement.model.dto.auth.AuthResponse;
import com.qoutraining.taskmanagement.model.dto.auth.LoginRequest;
import com.qoutraining.taskmanagement.model.dto.auth.RefreshRequest;
import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;

public interface AuthService {
    void register(UserRequestDto request);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshAccessToken(RefreshRequest request);
}
