package com.qoutraining.employeedirectory.service.impl;


import com.qoutraining.employeedirectory.exception.ResourceNotFoundException;
import com.qoutraining.employeedirectory.model.dto.payroll.PayrollRequestDTO;
import com.qoutraining.employeedirectory.model.mapper.PayrollMapper;
import com.qoutraining.employeedirectory.repository.PayrollRepository;
import com.qoutraining.employeedirectory.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PayrollServiceImplTest {

    @Mock
    private PayrollRepository payrollRepository;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private PayrollMapper payrollMapper;

    @InjectMocks
    private PayrollServiceImpl payrollService;

    private PayrollRequestDTO payrollRequest;

    @BeforeEach
    void setUp(){
        payrollRequest = new PayrollRequestDTO(
                1000L,
                LocalDate.now(),
                BigDecimal.valueOf(5000.0),
                BigDecimal.valueOf(200.0),
                BigDecimal.valueOf(50.0)
        );
    }

    @Test
    void addPayroll_EmployeeNotFound_ThrowsException() {
        when(employeeService.findEmployeeByID(payrollRequest.employeeId()))
                .thenThrow(new ResourceNotFoundException("Employee not found with id: "+ payrollRequest.employeeId()));

        ResourceNotFoundException exception=assertThrows(ResourceNotFoundException.class,
                () -> payrollService.addPayroll(payrollRequest));

        assertEquals("Employee not found with id: 1000",exception.getMessage());

        verifyNoInteractions(payrollRepository, payrollMapper);
    }




}