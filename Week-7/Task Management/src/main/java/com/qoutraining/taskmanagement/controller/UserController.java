package com.qoutraining.taskmanagement.controller;

import com.qoutraining.taskmanagement.model.dto.task.TaskRequestDto;
import com.qoutraining.taskmanagement.model.dto.task.TaskResponseDto;
import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.model.dto.user.UserResponseDto;
import com.qoutraining.taskmanagement.service.TaskService;
import com.qoutraining.taskmanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> findAllUsers(
            @PageableDefault(size = 10,page = 0,sort = "id" ,direction = Sort.Direction.ASC) Pageable pageable
    ){
        var response=userService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<UserResponseDto> findUserByEmail(@RequestParam String email){
        var response=userService.findUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyProfile(@AuthenticationPrincipal UserDetails userDetails){
        String email=userDetails.getUsername();
        var response=userService.findUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/me")
    public ResponseEntity<UserResponseDto> updateMyProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserRequestDto request){
        String email=userDetails.getUsername();
        var response=userService.updateUser(email,request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyProfile(@AuthenticationPrincipal UserDetails userDetails){
        String email= userDetails.getUsername();
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me/tasks")
    public ResponseEntity<Page<TaskResponseDto>> findTasksByUserEmail(
            @AuthenticationPrincipal UserDetails userDetails
            , @PageableDefault(size = 10, page = 0,sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        String email= userDetails.getUsername();
        var response = taskService.findTasksByUserEmail(email,pageable);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/me/tasks")
    public ResponseEntity<TaskResponseDto> addTask(
            @AuthenticationPrincipal UserDetails userDetails
            ,@Valid @RequestBody TaskRequestDto request){
        String email=userDetails.getUsername();
        var response= taskService.addTask(email, request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/me/tasks/{taskId}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @AuthenticationPrincipal UserDetails userDetails
            ,@PathVariable Long taskId
            ,@Valid @RequestBody TaskRequestDto request){
        String email=userDetails.getUsername();
        var response= taskService.updateTask(email,taskId,request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/me/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @AuthenticationPrincipal UserDetails userDetails
            , @PathVariable Long taskId){
        String email=userDetails.getUsername();
        taskService.deleteTask(email,taskId);
        return ResponseEntity.noContent().build();
    }
}
