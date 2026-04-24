package com.darknightcoder.mapper;

import com.darknightcoder.dto.OrderItemResponseDto;
import com.darknightcoder.dto.OrderResponseDto;
import com.darknightcoder.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface OrderMapper {


     OrderResponseDto mapToDto(Order order);
//    public static OrderResponseDto mapToDto(Order order){
//        OrderResponseDto responseDto = new OrderResponseDto();
//        List<OrderItemResponseDto> listOfOrderItems = order.getItems().stream()
//                .map(orderItem -> {
//                    OrderItemResponseDto orderItemResponse= new OrderItemResponseDto();
//                    orderItemResponse.setId(orderItem.getId());
//                    orderItemResponse.setProductId(orderItem.getProductId());
//                    orderItemResponse.setProductName(orderItem.getProductName());
//                    orderItemResponse.setPurchasePrice(orderItem.getPurchasePrice());
//                    orderItemResponse.setQuantity(orderItem.getQuantity());
//                    orderItemResponse.setSubtotal(orderItem.getSubtotal());
//                    return orderItemResponse;
//                }).toList();
//        responseDto.setId(order.getId());
//        responseDto.setItems(listOfOrderItems);
//        responseDto.setUserId(order.getUserId());
//        responseDto.setStatus(order.getStatus());
//        responseDto.setTotalAmount(order.getTotalAmount());
//        responseDto.setCreatedAt(order.getCreatedAt());
//        responseDto.setUpdatedAt(order.getUpdatedAt());
//        return responseDto;
//    }
}
