package com.qoutraining.employeedirectory.service;

import com.qoutraining.employeedirectory.model.dto.user.AuthResponse;
import com.qoutraining.employeedirectory.model.dto.user.LoginDTO;
import com.qoutraining.employeedirectory.model.dto.user.RefreshRequest;
import com.qoutraining.employeedirectory.model.dto.user.RegistrationDTO;
import com.qoutraining.employeedirectory.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    void register(RegistrationDTO request);
    AuthResponse login(LoginDTO request);
    AuthResponse refreshAccessToken(RefreshRequest request);
    void logoutUser(UserDetails userDetails);

}
