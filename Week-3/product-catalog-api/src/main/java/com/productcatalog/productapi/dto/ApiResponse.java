package com.productcatalog.productapi.dto;

public record ApiResponse<T>(
        boolean success,
        int statusCode,
        String message,
        T data,
        Object errors
) {

    public static <T> ApiResponse<T> success(int statusCode, String message,T data){
        return new ApiResponse<T>(true,statusCode,message,data,null);
    }

    public static <T> ApiResponse<T> error(int statusCode, String message, Object errors){
        return new ApiResponse<T>(false, statusCode,message,null,errors);
    }
}
