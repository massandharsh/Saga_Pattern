package dev.harsh.architect.orderservice.listeners;

import dev.harsh.architect.commondtos.dtos.PaymentResponseDto;
import dev.harsh.architect.orderservice.models.Order;
import dev.harsh.architect.orderservice.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaListener {
    private final OrderService orderService;
    @org.springframework.kafka.annotation.KafkaListener(topics="payment-event",groupId = "group2")
    public void listenersForOrderEvent(PaymentResponseDto paymentResponseDto){
        orderService.modifyOrder(paymentResponseDto);
    }

}
