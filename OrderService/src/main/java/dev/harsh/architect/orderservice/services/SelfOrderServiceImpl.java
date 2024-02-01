package dev.harsh.architect.orderservice.services;

import dev.harsh.architect.commondtos.dtos.OrderResponseDto;
import dev.harsh.architect.commondtos.dtos.PaymentResponseDto;
import dev.harsh.architect.commondtos.enums.OrderStatus;
import dev.harsh.architect.commondtos.enums.PaymentStatus;
import dev.harsh.architect.orderservice.dtos.OrderRequestDto;
import dev.harsh.architect.orderservice.events.OrderEvent;
import dev.harsh.architect.orderservice.models.Order;
import dev.harsh.architect.orderservice.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfOrderService")
@AllArgsConstructor
@Primary
public class SelfOrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderEvent orderEvent;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        //First we have to fetch price as well of the particular product
        //Lets say we are considering it to be between 1000 to 2000 units
        double price = Math.random() * 1000 + 1000;
        order.setPrice(price);
        //This order creation needs to be added in KAFKA Queue
        Order placedOrder =  orderRepository.save(order);
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                        .orderStatus(placedOrder.getOrderStatus())
                                .id(placedOrder.getId())
                                        .paymentStatus(PaymentStatus.PENDING)
                                                .amount(placedOrder.getPrice())
                                                        .build();

        orderEvent.addToKafka("order-event","default",orderResponseDto);
        return placedOrder;
    }

    @Override
    public void modifyOrder(PaymentResponseDto paymentResponseDto) {
        Order order = orderRepository.findById(paymentResponseDto.getOrderId()).orElse(null);
        assert order != null;
        if(paymentResponseDto.getPaymentStatus().equals(PaymentStatus.SUCCESS)){
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.setPaymentStatus(PaymentStatus.SUCCESS);
            orderRepository.save(order);
        }
        else{
            order.setOrderStatus(OrderStatus.FAILED);
            order.setPaymentStatus(PaymentStatus.FAILED);
            orderRepository.save(order);
        }
    }
}
