package com.darknightcoder.service;

import com.darknightcoder.dto.CreateOrderRequestDto;
import com.darknightcoder.dto.OrderResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDto createOrder(UUID userId, CreateOrderRequestDto requestDto);
    OrderResponseDto getOrderById(UUID orderId);
    List<OrderResponseDto> getOrderByUserId(UUID userId);
}
