package com.qoutraining.employeedirectory.exception;

public class EmployeeIsNotManagerException extends RuntimeException {
    public EmployeeIsNotManagerException(String message) {
        super(message);
    }
}
