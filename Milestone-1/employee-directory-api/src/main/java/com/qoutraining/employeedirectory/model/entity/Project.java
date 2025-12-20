package com.qoutraining.employeedirectory.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.qoutraining.employeedirectory.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false, length = 200)
    private String description;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "STATUS", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private List<EmployeeProject> employeeProjects;

}
