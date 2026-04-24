package com.darknightcoder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {
    private UUID id;
    private UUID productId;
    private String productName;
    private BigDecimal purchasePrice;
    private int quantity;
    private BigDecimal subtotal;
}
