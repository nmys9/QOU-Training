package com.qoutraining.employeedirectory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qoutraining.employeedirectory.model.dto.user.RegistrationDTO;
import com.qoutraining.employeedirectory.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class EmployeeDirectoryApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(roles = "ADMIN")
    void register_WhenValidRequest_ShouldCreateUser() throws Exception {
        RegistrationDTO request = new RegistrationDTO("New User","newuser@gmail.com","password123");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        assertTrue(userRepository.findByEmail("newuser@gmail.com").isPresent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void register_WhenInvalidEmail_ShouldReturnBadRequest() throws Exception {
        RegistrationDTO invalidRequest = new RegistrationDTO("Noor User", "invalid-email", "123");

        mockMvc.perform(post("/api/auth/register")
                        .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());

        assertTrue(userRepository.findByEmail("invalid-email").isEmpty());
    }
}
