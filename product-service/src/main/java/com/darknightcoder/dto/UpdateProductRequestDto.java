package com.darknightcoder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequestDto {
    private String name;
    private String productId;
    private String description;
    private BigDecimal price;
    private int stock;
}
