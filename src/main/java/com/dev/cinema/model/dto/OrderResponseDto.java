package com.dev.cinema.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long orderId;
    private LocalDateTime time;
    private List<TicketResponseDto> ticketsResponseDto;
    private Long userId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<TicketResponseDto> getTicketsResponseDto() {
        return ticketsResponseDto;
    }

    public void setTicketsResponseDto(List<TicketResponseDto> ticketsResponseDto) {
        this.ticketsResponseDto = ticketsResponseDto;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
