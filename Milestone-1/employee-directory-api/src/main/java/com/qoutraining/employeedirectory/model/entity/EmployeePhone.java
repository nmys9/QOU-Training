package com.qoutraining.employeedirectory.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qoutraining.employeedirectory.model.enums.PhoneType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE_PHONE")
public class EmployeePhone {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_phone_seq")
//    @SequenceGenerator(name = "emp_phone_seq", sequenceName = "EMP_PHONE_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHONE_ID")
    private Long id;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private Long phoneNumber;

    @Column(name = "PHONE_TYPE", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;


    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID",nullable = false)
    @JsonBackReference
    private Employee employee;
}
