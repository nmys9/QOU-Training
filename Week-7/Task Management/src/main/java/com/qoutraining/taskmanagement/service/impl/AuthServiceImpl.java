package com.qoutraining.taskmanagement.service.impl;

import com.qoutraining.taskmanagement.exception.DuplicateResourceException;
import com.qoutraining.taskmanagement.exception.PasswordNotMatchException;
import com.qoutraining.taskmanagement.exception.UserNotFoundException;
import com.qoutraining.taskmanagement.model.dto.auth.AuthResponse;
import com.qoutraining.taskmanagement.model.dto.auth.LoginRequest;
import com.qoutraining.taskmanagement.model.dto.auth.RefreshRequest;
import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.model.entity.RefreshToken;
import com.qoutraining.taskmanagement.model.entity.User;
import com.qoutraining.taskmanagement.model.enums.Role;
import com.qoutraining.taskmanagement.model.mapper.UserMapper;
import com.qoutraining.taskmanagement.repository.UserRepository;
import com.qoutraining.taskmanagement.security.util.JwtUtil;
import com.qoutraining.taskmanagement.service.AuthService;
import com.qoutraining.taskmanagement.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public void register(UserRequestDto request) {
        if(userRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("User with email: " + request.email() + " already exists.");
        }
        User user=userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public AuthResponse login(LoginRequest request) {
        User user=userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException("user not found."));

        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new PasswordNotMatchException("Incorrect Password");
        }
        String token= jwtUtil.generateToken(user);
        RefreshToken refreshToken=refreshTokenService.createRefreshToken(user.getId());

        return new AuthResponse(token,refreshToken.getToken());
    }

    @Override
    @Transactional
    public AuthResponse refreshAccessToken(RefreshRequest request) {
        RefreshToken refreshToken=refreshTokenService.validateRefreshToken(request.refreshToken());

        User user=refreshToken.getUser();
        String newAccessToken= jwtUtil.generateToken(user);

        return new AuthResponse(newAccessToken,request.refreshToken());
    }


}
