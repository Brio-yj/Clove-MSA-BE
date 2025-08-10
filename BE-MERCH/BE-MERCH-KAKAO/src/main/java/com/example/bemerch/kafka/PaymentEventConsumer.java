package com.example.bemerch.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentEventConsumer {
    @KafkaListener(topics = {"payment-success", "payment-refund"}, groupId = "merch-service")
    public void listen(String message) {
        log.info("Received payment event: {}", message);
    }
}
