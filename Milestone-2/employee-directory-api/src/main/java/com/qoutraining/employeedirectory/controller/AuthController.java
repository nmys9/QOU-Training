package com.qoutraining.employeedirectory.controller;

import com.qoutraining.employeedirectory.model.dto.user.AuthResponse;
import com.qoutraining.employeedirectory.model.dto.user.LoginDTO;
import com.qoutraining.employeedirectory.model.dto.user.RefreshRequest;
import com.qoutraining.employeedirectory.model.dto.user.RegistrationDTO;
import com.qoutraining.employeedirectory.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegistrationDTO request){
        authService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDTO request){
        var response=authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshRequest request){
        var response=authService.refreshAccessToken(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@AuthenticationPrincipal UserDetails userDetails){
        authService.logoutUser(userDetails);
        return ResponseEntity.noContent().build();
    }
}
