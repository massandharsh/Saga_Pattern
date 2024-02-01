package dev.harsh.architect.orderservice.events;

public interface Event {
    void addToKafka(String topic,String key,Object msg);
}
