package dev.harsh.architect.paymentservice.services;

import dev.harsh.architect.commondtos.dtos.OrderResponseDto;

public interface OrderProcessingService {
    void processOrder(OrderResponseDto orderResponseDto);
}
