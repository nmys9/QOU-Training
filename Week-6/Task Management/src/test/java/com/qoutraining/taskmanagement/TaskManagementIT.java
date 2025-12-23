package com.qoutraining.taskmanagement;

import com.qoutraining.taskmanagement.model.dto.task.TaskRequestDto;
import com.qoutraining.taskmanagement.model.dto.user.UserRequestDto;
import com.qoutraining.taskmanagement.model.dto.user.UserResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.ObjectMapper;


import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class TaskManagementIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final UserRequestDto userRequest=new UserRequestDto("Noor Sakhel","Noor@gmail.com","0595686355");

    @Test
    void shouldCreateUserThenCreateTaskSuccessfully() throws Exception{

        MvcResult useResult= mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        String userJsonResponse=useResult.getResponse().getContentAsString();
        UserResponseDto userResponse= objectMapper.readValue(userJsonResponse, UserResponseDto.class);
        Long userId=userResponse.id();


        TaskRequestDto taskRequest = new TaskRequestDto(userId, "New Task", "Desc",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "HIGH", "PENDING");


        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Task"))
                .andExpect(jsonPath("$.id").exists());

    }
    @Test
    void shouldFailCreatingTaskForNonExistingUser() throws Exception{
        TaskRequestDto taskRequest = new TaskRequestDto(99L, "New Task", "Desc",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "HIGH", "PENDING");

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldReturnAllTasksForUser() throws Exception{
        MvcResult userResult= mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        String userJsonResponse=userResult.getResponse().getContentAsString();
        UserResponseDto userResponse=objectMapper.readValue(userJsonResponse, UserResponseDto.class);

        Long userId= userResponse.id();

        TaskRequestDto task1 = new TaskRequestDto(userId, "Task 1", "Desc 1",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1), "HIGH", "PENDING");

        TaskRequestDto task2 = new TaskRequestDto(userId, "Task 2", "Desc 2",
                LocalDateTime.now(), LocalDateTime.now().plusDays(2), "MEDIUM", "PENDING");


        mockMvc.perform(post("/api/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(task1)))
                .andExpect(status().isCreated());


        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task2)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/users/{userId}/tasks",userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].title").value("Task 1"))
                .andExpect(jsonPath("$.content[1].title").value("Task 2"));


    }
}