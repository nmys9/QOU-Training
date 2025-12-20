package com.qoutraining.taskmanagement.controller;

import com.qoutraining.taskmanagement.model.dto.task.TaskResponseDto;
import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.model.dto.user.UserResponseDto;
import com.qoutraining.taskmanagement.service.TaskService;
import com.qoutraining.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        var response = userService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id ){
        var response = userService.findById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<TaskResponseDto>> findTasksByUserId(@PathVariable Long userId){
        var response = taskService.findTasksByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto dto){
        var response = userService.addUser(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto dto){
        var response = userService.updateUser(id,dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
