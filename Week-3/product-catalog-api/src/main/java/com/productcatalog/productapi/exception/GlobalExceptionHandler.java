package com.productcatalog.productapi.exception;


import com.productcatalog.productapi.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Object>> notFound(NoSuchElementException ex){

        ApiResponse<Object> response=ApiResponse.error(
                HttpStatus.NOT_FOUND.value(),//404
                "Operation failed: The required resource was not found.",
                ex.getMessage()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> badRequest(IllegalArgumentException ex){
        ApiResponse<Object> response=ApiResponse.error(
                HttpStatus.BAD_REQUEST.value(),//400
                "Process failed: Request input error.",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ApiResponse<Object>> conflict(ResourceConflictException ex){
        ApiResponse<Object> response=ApiResponse.error(
                HttpStatus.CONFLICT.value(),//409
                "Resource conflict: The resource already exists.",
                ex.getMessage()
        );
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handelValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName=((FieldError) error).getField();
            String errorMessage= error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });

        ApiResponse<Object> response=ApiResponse.error(
                HttpStatus.BAD_REQUEST.value(),//400
                "Input validation failed. Please review the details.",
                errors
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleMissingBody(HttpMessageNotReadableException ex) {
        String customMessage = "Malformed JSON or missing request body. Please ensure your request body is correctly formatted and not empty.";

        ApiResponse<Object> response = ApiResponse.error(
                HttpStatus.BAD_REQUEST.value(), // 400
                customMessage,
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handelGenericException(Exception ex){
        ApiResponse<Object> response=ApiResponse.error(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected server error occurred. Please try again later.",
                ex.getMessage()
        );
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
