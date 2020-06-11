package com.dev.cinema.model.dto;

import com.dev.cinema.model.Ticket;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long orderId;
    private LocalDateTime time;
    private List<Ticket> ticketsResponseDto;
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

    public List<Ticket> getTicketsResponseDto() {
        return ticketsResponseDto;
    }

    public void setTicketsResponseDto(List<Ticket> ticketsResponseDto) {
        this.ticketsResponseDto = ticketsResponseDto;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
