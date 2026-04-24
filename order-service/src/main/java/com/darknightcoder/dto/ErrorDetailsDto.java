package com.darknightcoder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetailsDto {
    private Instant timestamp;
    private String errorMessage;
    private String uri;
}
