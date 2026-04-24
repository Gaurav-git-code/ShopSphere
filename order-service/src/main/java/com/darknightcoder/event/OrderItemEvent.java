package com.darknightcoder.event;

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
public class OrderItemEvent {
    private UUID productId;
    private String productName;
    private int quantity;
    private BigDecimal priceAtPurchase;
}
