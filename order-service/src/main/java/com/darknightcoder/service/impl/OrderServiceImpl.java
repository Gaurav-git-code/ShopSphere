package com.darknightcoder.service.impl;

import com.darknightcoder.client.ProductServiceClient;
import com.darknightcoder.constant.OrderStatus;
import com.darknightcoder.dto.CreateOrderRequestDto;
import com.darknightcoder.dto.OrderResponseDto;
import com.darknightcoder.dto.ProductResponseDto;
import com.darknightcoder.entity.Order;
import com.darknightcoder.entity.OrderItems;
import com.darknightcoder.event.OrderEventPublisher;
import com.darknightcoder.event.OrderItemEvent;
import com.darknightcoder.event.OrderPlacedEvent;
import com.darknightcoder.exception.ResourceNotFoundException;
import com.darknightcoder.exception.ServiceUnavailableException;
import com.darknightcoder.mapper.OrderMapper;
import com.darknightcoder.repository.OrderRepository;
import com.darknightcoder.service.OrderService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductFetchService productFetchService;
    private OrderMapper mapper;
    private OrderEventPublisher publisher;
    @Override
    public OrderResponseDto createOrder(UUID userId, CreateOrderRequestDto requestDto) {
        List<OrderItems> orderItemsList =
                requestDto.getOrderItems()
                        .stream().map(orderItem ->
                        {
                            ProductResponseDto productResponse =
                                    productFetchService.fetchProduct(orderItem.getProductId());
                            OrderItems orderItems = new OrderItems();
                            orderItems.setProductId(orderItem.getProductId());
                            orderItems.setProductName(productResponse.getName());
                            orderItems.setQuantity(orderItem.getQuantity());
                            orderItems.setPurchasePrice(productResponse.getPrice());
                            orderItems.setSubtotal(
                                    productResponse.getPrice()
                                            .multiply(BigDecimal.valueOf(orderItem.getQuantity()))
                                            .setScale(2, RoundingMode.HALF_UP)
                            );
                            return orderItems;

                        }).toList();
        Order order = new Order();
        order.setTotalAmount(orderItemsList.stream()
                .map(OrderItems::getSubtotal)
                .reduce(BigDecimal.ZERO,BigDecimal::add)
        );
        order.setItems(orderItemsList);
        order.setStatus(OrderStatus.PENDING);
        order.setUserId(userId);
        orderItemsList.forEach(item -> item.setOrder(order));
        Order savedOrder = orderRepository.save(order);

        OrderPlacedEvent orderPlacedEvent = OrderPlacedEvent.builder()
                .orderId(savedOrder.getId())
                .userId(savedOrder.getUserId())
                .totalAmount(savedOrder.getTotalAmount())
                .placedAt(savedOrder.getCreatedAt())
                .items(savedOrder.getItems().stream()
                        .map(item -> new OrderItemEvent(
                                item.getProductId(),
                                item.getProductName(),
                                item.getQuantity(),
                                item.getPurchasePrice()
                        )).toList()
                ).build();
        publisher.publishOrderPlaced(orderPlacedEvent);
        return mapper.mapToDto(savedOrder);
    }



    @Override
    public OrderResponseDto getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Id",orderId));
        return mapper.mapToDto(order);
    }

    @Override
    public List<OrderResponseDto> getOrderByUserId(UUID userId) {
        List<Order> orderList = orderRepository.findByUserId(userId);
        if(orderList == null || orderList.isEmpty())
            throw new ResourceNotFoundException("User","id",userId);
        return orderList.stream()
                .map(mapper::mapToDto).toList();
    }
}
