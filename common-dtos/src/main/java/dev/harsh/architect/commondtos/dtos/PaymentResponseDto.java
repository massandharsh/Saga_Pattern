package dev.harsh.architect.commondtos.dtos;

import dev.harsh.architect.commondtos.enums.OrderStatus;
import dev.harsh.architect.commondtos.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {
    private Long orderId;
    private PaymentStatus paymentStatus;
}
