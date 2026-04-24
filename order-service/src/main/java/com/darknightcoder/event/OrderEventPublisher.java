package com.darknightcoder.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class OrderEventPublisher {
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    private static final String TOPIC = "order-event";

    public void publishOrderPlaced (OrderPlacedEvent event){
        kafkaTemplate.send(TOPIC, event.getOrderId().toString(),event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("failed to publish Order Publish Event for Order Id :{}",event.getOrderId(),ex);
                    } else {
                        log.info("Published OrderPlacedEvent for orderId: {} to partition: {}",
                                event.getOrderId(), result.getRecordMetadata().partition());
                    }
                });
    }

}
