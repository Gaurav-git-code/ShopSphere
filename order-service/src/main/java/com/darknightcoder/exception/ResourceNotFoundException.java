package com.darknightcoder.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resource, String field, Object object){
        super(String.format("%s with %s : %s was not found !",resource, field, object));
    }
}
