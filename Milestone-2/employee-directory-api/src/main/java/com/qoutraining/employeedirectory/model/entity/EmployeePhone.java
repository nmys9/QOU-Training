package com.qoutraining.employeedirectory.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qoutraining.employeedirectory.model.enums.PhoneType;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE_PHONE")
public class EmployeePhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHONE_ID")
    private Long id;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private Long phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "PHONE_TYPE", nullable = false, length = 20)
    private PhoneType phoneType;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID",nullable = false)
    @JsonIgnoreProperties("employeePhones")
    private Employee employee;

    public PhoneType getPhoneType() {
        return phoneType;
    }
}
