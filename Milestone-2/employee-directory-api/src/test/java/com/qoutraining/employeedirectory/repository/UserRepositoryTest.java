package com.qoutraining.employeedirectory.repository;

import com.qoutraining.employeedirectory.config.TestAuditConfig;
import com.qoutraining.employeedirectory.model.entity.Role;
import com.qoutraining.employeedirectory.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestAuditConfig.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp(){
        Role role=Role.builder().name("USER").build();
        roleRepository.save(role);

        User user=User.builder()
                .email("noor@gmail.com")
                .password("noor123")
                .roles(List.of(role))
                .enabled(true)
                .build();
        userRepository.save(user);
    }

    @Test
    void findByEmail_WhenUserExists_ReturnsUser(){
        Optional<User> user=userRepository.findByEmail("noor@gmail.com");

        assertTrue(user.isPresent());
        assertEquals("noor@gmail.com",user.get().getEmail());
    }

    @Test
    void findByEmail_WhenUserNotExists_ReturnsEmpty(){
        Optional<User> result = userRepository.findByEmail("notfound@gmail.com");

        assertTrue(result.isEmpty());
    }
}
