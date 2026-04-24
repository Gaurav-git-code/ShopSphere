package com.darknightcoder.consumer;

import com.darknightcoder.entity.ProcessedEvent;
import com.darknightcoder.event.OrderPlacedEvent;
import com.darknightcoder.repository.ProcessedEventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
@AllArgsConstructor
public class OrderEventConsumer {
    private ProcessedEventRepository eventRepository;

    @KafkaListener(
            topics = "order-event",
            groupId = "notification-group"
    )
    public void consumeOrderPlacedEvent (OrderPlacedEvent event){
        log.info("Received OrderPlacedEvent for orderId: {}",event.getOrderId());
        if(eventRepository.existsById(event.getOrderId())){
            log.warn("Event for orderId: {} already processed, skipping", event.getOrderId());
            return;
        }
        //simulate sending email
        log.info("Sending confirmation email for orderId: {}",event.getOrderId());
        log.info("Order total: {} with {} items",
                event.getTotalAmount(),
                event.getItems().size());
        event.getItems().forEach(item -> log.info("- {} x {} at {}",
                item.getQuantity(),
                item.getProductName(),
                item.getPriceAtPurchase()));

        eventRepository.save(
                new ProcessedEvent(event.getOrderId(), Instant.now())
        );
        log.info("Email sent successfully for orderId: {}", event.getOrderId());
    }
}
