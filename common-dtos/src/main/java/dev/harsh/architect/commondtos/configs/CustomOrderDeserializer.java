package dev.harsh.architect.commondtos.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.harsh.architect.commondtos.dtos.OrderResponseDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomOrderDeserializer implements Deserializer<OrderResponseDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public OrderResponseDto deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), OrderResponseDto.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }
}
