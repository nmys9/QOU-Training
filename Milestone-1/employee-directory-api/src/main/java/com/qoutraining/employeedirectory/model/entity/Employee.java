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
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
//    @SequenceGenerator(name = "emp_seq",sequenceName = "EMP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 30)
    private String email;

    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "HIRE_DATE", nullable = false)
    private LocalDate hireDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "END_DATE", nullable = true)
    private LocalDate endDate;


    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    @JsonBackReference
    private Department department;


    @ManyToOne
    @JoinColumn(name = "JOB_TITLE_ID", nullable = false)
    @JsonBackReference
    private JobTitle jobTitle;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee")
    private List<EmployeePhone> employeePhones;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee" )
    private List<EmployeeProject> employeeProjects;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee")
    private List<Payroll> payrolls;

}
