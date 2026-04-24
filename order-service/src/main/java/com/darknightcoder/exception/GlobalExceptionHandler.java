package com.darknightcoder.exception;

import com.darknightcoder.dto.ErrorDetailsDto;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDto> resourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest request
    ){
        ErrorDetailsDto error = new ErrorDetailsDto(
                Instant.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ErrorDetailsDto> serviceUnavailableException(
            ServiceUnavailableException exception,
            WebRequest request
    ){
        ErrorDetailsDto error = new ErrorDetailsDto(
                Instant.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler(RateLimitException.class)
    public ResponseEntity<ErrorDetailsDto> rateLimiterException(RateLimitException exception, WebRequest request){
        ErrorDetailsDto error = new ErrorDetailsDto(
                Instant.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.TOO_MANY_REQUESTS);
    }

}
