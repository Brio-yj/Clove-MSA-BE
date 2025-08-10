package com.example.bemsaseat.kafka;

import com.example.bemsaseat.seat.dto.MinimalSeatDTO;
import com.example.bemsaseat.seat.service.SeatService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {
    private final SeatService seatService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "payment-refund", groupId = "seat-service")
    public void handleRefund(String message) throws Exception {
        List<MinimalSeatDTO> seatDTOs = objectMapper.readValue(message, new TypeReference<List<MinimalSeatDTO>>() {});
        for (MinimalSeatDTO dto : seatDTOs) {
            log.info("Processing refund for seat {}", dto);
            seatService.cancelSeat(dto);
        }
    }
}
