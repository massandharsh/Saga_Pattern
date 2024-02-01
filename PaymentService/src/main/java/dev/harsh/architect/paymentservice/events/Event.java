package dev.harsh.architect.paymentservice.events;

public interface Event {
    void addToKafka(String topic,String key,Object msg);
}
