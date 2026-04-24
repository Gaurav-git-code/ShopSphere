package com.darknightcoder.exception;

public class ServiceUnavailableException extends RuntimeException{
    public ServiceUnavailableException(String resource){
        super(String.format("%s Service is currently unavailable.",resource));
    }
}
