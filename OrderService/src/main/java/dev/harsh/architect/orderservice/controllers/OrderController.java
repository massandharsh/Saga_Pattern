package dev.harsh.architect.orderservice.controllers;

import dev.harsh.architect.orderservice.dtos.OrderRequestDto;
import dev.harsh.architect.orderservice.models.Order;
import dev.harsh.architect.orderservice.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrder();
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto.toOrder());
    }
}
