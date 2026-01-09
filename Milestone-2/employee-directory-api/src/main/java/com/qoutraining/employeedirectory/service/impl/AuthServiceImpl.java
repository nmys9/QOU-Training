package com.qoutraining.employeedirectory.service.impl;

import com.qoutraining.employeedirectory.exception.DuplicateResourceException;
import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.user.AuthResponse;
import com.qoutraining.employeedirectory.model.dto.user.LoginDTO;
import com.qoutraining.employeedirectory.model.dto.user.RefreshRequest;
import com.qoutraining.employeedirectory.model.dto.user.RegistrationDTO;
import com.qoutraining.employeedirectory.model.entity.Employee;
import com.qoutraining.employeedirectory.model.entity.RefreshToken;
import com.qoutraining.employeedirectory.model.entity.User;
import com.qoutraining.employeedirectory.model.entity.Role;
import com.qoutraining.employeedirectory.repository.EmployeeRepository;
import com.qoutraining.employeedirectory.repository.RoleRepository;
import com.qoutraining.employeedirectory.repository.UserRepository;
import com.qoutraining.employeedirectory.security.JwtUtil;
import com.qoutraining.employeedirectory.service.AuthService;
import com.qoutraining.employeedirectory.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public void register(RegistrationDTO request) {
        if(userRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("User with email: " + request.email() + " already exists.");
        }
        Role userRole=roleRepository.findByName("USER").orElseThrow(
                () -> new ResourceNotFoundException("Default Role USER not found.")
        );

        User user=new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRoles(List.of(userRole));
        userRepository.save(user);

        Employee employee=new Employee();
        employee.setFullName(request.fullName());
        employee.setUser(user);
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public AuthResponse login(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user=userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

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
    @Transactional
    public void logoutUser(UserDetails userDetails) {
        if(userDetails==null){
            throw new AuthenticationCredentialsNotFoundException("User not authenticated");
        }
        User user=userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        refreshTokenService.deleteByUser(user);
    }

    @Override
    @Transactional
    public void assignRoleToUser(Long userId, String roleName) {
        User user=userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found.")
        );

        Role role=roleRepository.findByName(roleName).orElseThrow(
                () -> new ResourceNotFoundException("Role not found.")
        );

        if(user.getRoles() == null){
            user.setRoles(new ArrayList<>());
        }

        if(user.getRoles().stream().anyMatch(r -> r.getName().equals(roleName))){
            throw new DuplicateResourceException("User already has role: " + roleName);
        }

        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeRoleFromUser(Long userId, String roleName) {
        User user=userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found.")
        );

        Role role=roleRepository.findByName(roleName).orElseThrow(
                () -> new ResourceNotFoundException("Role not found.")
        );

        if(user.getRoles().stream().noneMatch(r -> r.getName().equals(roleName))){
            throw new ResourceNotFoundException("User dose not have role: " + roleName);
        }

        user.getRoles().remove(role);
        userRepository.save(user);
    }


}
