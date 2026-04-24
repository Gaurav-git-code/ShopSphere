package com.darknightcoder.exception;

import com.darknightcoder.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> resourceNotFoundException(ResourceNotFoundException exception,
                                                              WebRequest request){
        ErrorDto error = ErrorDto.builder()
                .timeStamp(Instant.now())
                .errorMessage(exception.getMessage())
                .uri(request.getDescription(false))
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
