package com.example.demo.demo_api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse notFound(NoSuchElementException ex){
        return ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse validate(IllegalArgumentException ex){
        return ErrorResponse.create(ex, HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse exception(Exception ex){
        return ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR , "An unexpected error occurred:" + ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        // 1. استخلاص رسائل الأخطاء وتجميعها
        String errorDetails = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; ")); // تجميع الأخطاء في سلسلة نصية مفصولة بـ ";"

        // 2. بناء كائن الخطأ الموحد (باستخدام ErrorResponse الخاص بك)
        //    هنا نستخدم رسالة الأخطاء المجمعة
        ErrorResponse customError = ErrorResponse.create(
                ex,
                HttpStatus.BAD_REQUEST, // نحدد الرمز 400
                "Validation Failed: " + errorDetails // الرسالة المفصلة
        );

        // 3. إرجاع كائن الرد مع رمز الحالة 400
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

}
