package com.darknightcoder.dto;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {
    private Instant timeStamp;
    private String errorMessage;
    private String uri;

}
