package com.qoutraining.employeedirectory.model.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Column(name = "FULL_NAME", nullable = false, length = 50)
    private String fullName;

    @Column(name = "ADDRESS", length = 100)
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "HIRE_DATE")
    private LocalDate hireDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "END_DATE")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "JOB_TITLE_ID")
    private JobTitle jobTitle;

    @OneToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    @OneToMany(mappedBy = "employee")
    private List<EmployeePhone> employeePhones;

    @OneToMany(mappedBy = "employee" )
    private List<EmployeeProject> employeeProjects;

    @OneToMany(mappedBy = "employee")
    private List<Payroll> payrolls;

}
