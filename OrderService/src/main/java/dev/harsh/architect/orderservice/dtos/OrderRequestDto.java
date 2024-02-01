package dev.harsh.architect.orderservice.dtos;

import dev.harsh.architect.commondtos.enums.OrderStatus;
import dev.harsh.architect.commondtos.enums.PaymentStatus;
import dev.harsh.architect.orderservice.models.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {
    private Long userId;
    private Long productId;
    public Order toOrder(){
        return Order.builder()
                .orderStatus(OrderStatus.PENDING)
                .paymentStatus(PaymentStatus.PENDING)
                .productId(productId)
                .userId(userId)
                .build();
    }
}
