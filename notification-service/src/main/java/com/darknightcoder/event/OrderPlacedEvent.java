package com.darknightcoder.event;


import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPlacedEvent {
    private UUID orderId;
    private UUID userId;
    private BigDecimal totalAmount;
    private Instant placedAt;
    private List<OrderItemEvent> items;
}
