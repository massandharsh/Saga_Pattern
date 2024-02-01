package dev.harsh.architect.commondtos.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.harsh.architect.commondtos.dtos.PaymentResponseDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class CustomPaymentSerializer implements Serializer<PaymentResponseDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public byte[] serialize(String topic, PaymentResponseDto data) {
        try {
            if (data == null){
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }
    }
}
