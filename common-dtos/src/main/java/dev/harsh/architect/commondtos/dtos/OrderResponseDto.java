package dev.harsh.architect.commondtos.dtos;

import dev.harsh.architect.commondtos.enums.OrderStatus;
import dev.harsh.architect.commondtos.enums.PaymentStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Long productId;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private Double amount;
}
