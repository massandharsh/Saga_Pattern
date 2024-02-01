package dev.harsh.architect.orderservice.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.harsh.architect.commondtos.enums.OrderStatus;
import dev.harsh.architect.commondtos.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "orders")
@ToString(callSuper = false)
@JsonDeserialize(as = Order.class)
public class Order extends BaseModel implements Serializable {
    private Long userId;
    private Long productId;
    private Double price;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;
}
