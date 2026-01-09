package com.qoutraining.employeedirectory.init;

import com.qoutraining.employeedirectory.model.entity.Employee;
import com.qoutraining.employeedirectory.model.entity.Role;
import com.qoutraining.employeedirectory.model.entity.User;
import com.qoutraining.employeedirectory.repository.EmployeeRepository;
import com.qoutraining.employeedirectory.repository.RoleRepository;
import com.qoutraining.employeedirectory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(!roleRepository.existsRoleByName("USER")){
            roleRepository.save(Role.builder().name("USER").build());
        }
        if(!roleRepository.existsRoleByName("ADMIN")){
            roleRepository.save(Role.builder().name("ADMIN").build());
        }
        Role userRole=roleRepository.findByName("USER").orElseThrow();
        Role adminRole=roleRepository.findByName("ADMIN").orElseThrow();

        if(userRepository.count() == 0){
            User admin=User.builder()
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("admin123"))
                    .roles(List.of(adminRole,userRole))
                    .enabled(true)
                    .build();

            userRepository.save(admin);

            Employee employee=Employee.builder()
                    .fullName("Admin")
                    .user(admin)
                    .build();
            employeeRepository.save(employee);
            System.out.println("Admin Created: admin/admin123");
        }
    }
}
