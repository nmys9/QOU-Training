package com.qoutraining.taskmanagement.controller;

import com.qoutraining.taskmanagement.model.dto.auth.AuthResponse;
import com.qoutraining.taskmanagement.model.dto.auth.LoginRequest;
import com.qoutraining.taskmanagement.model.dto.auth.RefreshRequest;
import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.service.AuthService;
import com.qoutraining.taskmanagement.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRequestDto request){
        authService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        var response= authService.login(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshRequest request){
        var response=authService.refreshAccessToken(request);
        return ResponseEntity.ok(response);
    }
}
