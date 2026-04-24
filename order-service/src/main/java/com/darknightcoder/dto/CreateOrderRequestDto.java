package com.darknightcoder.dto;

import com.darknightcoder.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequestDto {
    private List<OrderItemRequestDto> orderItems;
}
