package com.darknightcoder.controller;

import com.darknightcoder.dto.CreateOrderRequestDto;
import com.darknightcoder.dto.OrderResponseDto;
import com.darknightcoder.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestHeader ("X-User-Id") UUID userId,
            @RequestBody CreateOrderRequestDto requestDto){
        return new ResponseEntity<>(orderService.createOrder(userId, requestDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable UUID orderId){
        return new ResponseEntity<>(orderService.getOrderById(orderId),HttpStatus.OK);
    }
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrderByUserId(@PathVariable UUID userId){
        return new ResponseEntity<>(orderService.getOrderByUserId(userId),HttpStatus.OK);
    }
}
