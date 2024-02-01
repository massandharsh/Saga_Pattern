package dev.harsh.architect.paymentservice.services;

import com.zaxxer.hikari.util.IsolationLevel;
import dev.harsh.architect.commondtos.dtos.OrderResponseDto;
import dev.harsh.architect.commondtos.dtos.PaymentResponseDto;
import dev.harsh.architect.commondtos.enums.PaymentStatus;
import dev.harsh.architect.paymentservice.events.PaymentEvent;
import dev.harsh.architect.paymentservice.listeners.KafkaListener;
import dev.harsh.architect.paymentservice.models.UserBalance;
import dev.harsh.architect.paymentservice.repositories.UserBalanceRepository;
import dev.harsh.architect.paymentservice.repositories.UserTransactionRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SelfOrderProcessingService implements OrderProcessingService {
    private final UserBalanceRepository userBalanceRepository;
    private final UserTransactionRepository userTransactionRepository;
    private final PaymentEvent paymentEvent;
    @Override
    @SneakyThrows
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void processOrder(OrderResponseDto orderResponseDto) {
        double amount = orderResponseDto.getAmount();
        UserBalance userBalance = userBalanceRepository.findById(orderResponseDto.getUserId()).orElse(null);
        if(userBalance != null){
            double currentBalance = userBalance.getBalance();
            if(currentBalance >= amount){
                userBalance.setBalance(currentBalance-amount);
                userBalanceRepository.save(userBalance);
                PaymentResponseDto paymentResponseDto = PaymentResponseDto.builder()
                        .orderId(orderResponseDto.getId())
                                .paymentStatus(PaymentStatus.SUCCESS)
                                        .build();
                paymentEvent.addToKafka("payment-event","default",paymentResponseDto);
            }
            else{
                //Emit the event to cancel it
                PaymentResponseDto paymentResponseDto = PaymentResponseDto.builder()
                        .orderId(orderResponseDto.getId())
                        .paymentStatus(PaymentStatus.FAILED)
                        .build();
                paymentEvent.addToKafka("payment-event","default",paymentResponseDto);
            }
        }
    }
}