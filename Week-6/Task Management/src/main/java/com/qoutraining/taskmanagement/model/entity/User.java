package com.qoutraining.taskmanagement.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq",sequenceName = "USER_SEQ",allocationSize = 1)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FULL_NAME", nullable = false, length = 100)
    private String fullName;

    @Email(message = "Email format is invalid")
    @Column(name = "EMAIL",unique = true,nullable = false, length = 100)
    private String email;

    @Column(name = "PHONE",nullable = false,length = 10)
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
}
