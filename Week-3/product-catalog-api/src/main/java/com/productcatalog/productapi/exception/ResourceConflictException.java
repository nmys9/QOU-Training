package com.productcatalog.productapi.exception;

public class ResourceConflictException extends RuntimeException{
    public ResourceConflictException(String resourceType,String objectName, Object id){
        super(resourceType + " with " + objectName + " :" + id + " already exists. Cannot add duplicate.");
    }
}
