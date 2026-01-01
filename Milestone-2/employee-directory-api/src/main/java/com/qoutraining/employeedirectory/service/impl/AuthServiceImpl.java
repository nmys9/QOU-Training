package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.DuplicateResourceException;
import com.qoutraining.employeedirectory.exception.PasswordNotMatchException;
import com.qoutraining.employeedirectory.model.dto.user.AuthResponse;
import com.qoutraining.employeedirectory.model.dto.user.LoginDTO;
import com.qoutraining.employeedirectory.model.dto.user.RefreshRequest;
import com.qoutraining.employeedirectory.model.dto.user.RegistrationDTO;
import com.qoutraining.employeedirectory.model.entity.Employee;
import com.qoutraining.employeedirectory.model.entity.RefreshToken;
import com.qoutraining.employeedirectory.model.entity.User;
import com.qoutraining.employeedirectory.model.enums.Role;
import com.qoutraining.employeedirectory.repository.EmployeeRepository;
import com.qoutraining.employeedirectory.repository.UserRepository;
import com.qoutraining.employeedirectory.security.JwtUtil;
import com.qoutraining.employeedirectory.service.AuthService;
import com.qoutraining.employeedirectory.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public void register(RegistrationDTO request) {
        if(userRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("User with email: " + request.email() + " already exists.");
        }
        User user=new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        userRepository.save(user);

        Employee employee=new Employee();
        employee.setFullName(request.fullName());
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public AuthResponse login(LoginDTO request) {
        User user=userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        if(!passwordEncoder.matches(request.password(),user.getPassword())){
            throw new PasswordNotMatchException("Incorrect Password");
        }
        String token= jwtUtil.generateToken(user);
        RefreshToken refreshToken=refreshTokenService.createRefreshToken(user);

        return new AuthResponse(token,refreshToken.getToken());
    }

    @Override
    @Transactional
    public AuthResponse refreshAccessToken(RefreshRequest request) {
        RefreshToken refreshToken=refreshTokenService.validateRefreshToken(request.refreshToken());

        User user=refreshToken.getUser();
        String newAccessToken= jwtUtil.generateToken(user);

        return new AuthResponse(newAccessToken,refreshToken.getToken());
    }

    @Override
    public void logoutUser(UserDetails userDetails) {
        if(userDetails==null){
            throw new RuntimeException("User not authenticated");
        }
        User user=userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        refreshTokenService.deleteByUser(user);
    }
}
