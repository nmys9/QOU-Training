package com.qoutraining.employeedirectory.exception;

import com.qoutraining.employeedirectory.model.dto.APIErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                            HttpServletRequest request){
        APIErrorResponse errorResponse= new APIErrorResponse(
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),//404
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidEndDateException.class)
    public ResponseEntity<APIErrorResponse> handleInvalidEndDateException(InvalidEndDateException ex,
                                                                           HttpServletRequest request){
        APIErrorResponse errorResponse = new APIErrorResponse(
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),//400
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EmployeeIsNotManagerException.class)
    public ResponseEntity<APIErrorResponse> handleEmployeeIsNotManagerException(EmployeeIsNotManagerException ex,
                                                                          HttpServletRequest request){
        APIErrorResponse errorResponse = new APIErrorResponse(
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),//400
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex,
                                                                   HttpServletRequest request) {

        Map<String,String> errors=new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName=((FieldError) error).getField();
            String errorMessage= error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });

        APIErrorResponse errorResponse= new APIErrorResponse(
                "Input validation failed. Please review the details.",
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),//400
                LocalDateTime.now(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIErrorResponse> handleJsonReadError(HttpMessageNotReadableException ex,
                                                      HttpServletRequest request) {
        APIErrorResponse errorResponse=new APIErrorResponse(
                "JSON Parse Error: Malformed JSON or invalid data type for field 'hireDate'.",
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIErrorResponse> handleGenericException(Exception ex,
                                                                   HttpServletRequest request){
        String genericMessage = "An unexpected error occurred on the server.";

        APIErrorResponse errorResponse = new APIErrorResponse(
                genericMessage,
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),//500
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
