package dev.harsh.architect.orderservice.services;

import dev.harsh.architect.commondtos.dtos.PaymentResponseDto;
import dev.harsh.architect.orderservice.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrder();

    Order createOrder(Order order);

    void modifyOrder(PaymentResponseDto paymentResponseDto);
}
