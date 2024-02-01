package dev.harsh.architect.paymentservice.listeners;

import dev.harsh.architect.commondtos.dtos.OrderResponseDto;
import dev.harsh.architect.paymentservice.services.OrderProcessingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaListener {
    private final OrderProcessingService orderProcessingService;
    @org.springframework.kafka.annotation.KafkaListener(topics="order-event",groupId = "group1")
    public void listenersForOrderEvent(OrderResponseDto order){
        orderProcessingService.processOrder(order);
    }

}
