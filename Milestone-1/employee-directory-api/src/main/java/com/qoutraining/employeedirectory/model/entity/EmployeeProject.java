package com.qoutraining.employeedirectory.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qoutraining.employeedirectory.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE_PROJECT")
public class EmployeeProject {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_proj_seq")
//    @SequenceGenerator(name = "emp_proj_seq", sequenceName = "EMP_PROJ_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    @JsonBackReference
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID", nullable = false)
    @JsonBackReference
    private Project project;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS",nullable = false, length = 20)
    private Status status;

}
