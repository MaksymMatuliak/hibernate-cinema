package com.dev.cinema.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long orderId;
    private LocalDateTime time;
    private List<TicketResponseDto> ticketsResponseDto;
    private Long userId;
}
