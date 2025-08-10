package com.example.bemsaticket.kafka;

import com.example.bemsaticket.ticket.dto.MinimalSeatDTO;
import com.example.bemsaticket.ticket.service.TicketService;
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
    private final TicketService ticketService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "payment-success", groupId = "ticket-service")
    public void handlePaymentSuccess(String message) throws Exception {
        List<MinimalSeatDTO> seatDTOs = objectMapper.readValue(message, new TypeReference<List<MinimalSeatDTO>>() {});
        log.info("Consuming payment-success event for {} seats", seatDTOs.size());
        ticketService.saveTicketsFromEvent(seatDTOs);
    }
}
