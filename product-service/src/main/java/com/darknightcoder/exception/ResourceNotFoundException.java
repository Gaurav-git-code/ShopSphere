package com.darknightcoder.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resource, String field, Object object){
        super(String.format("%s is not found for the field %s with vale : %s",resource,field,object));
    }
}
