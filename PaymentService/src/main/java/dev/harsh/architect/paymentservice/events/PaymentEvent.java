package dev.harsh.architect.paymentservice.events;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentEvent implements Event {
    private final KafkaTemplate<String,Object> kafkaTemplate;
    @Override
    public void addToKafka(String topic, String key, Object msg) {
        kafkaTemplate.send(topic,key,msg);
    }
}
