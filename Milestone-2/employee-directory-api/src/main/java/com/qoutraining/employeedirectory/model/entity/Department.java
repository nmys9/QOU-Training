package com.qoutraining.employeedirectory.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true, length = 50)
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

}

