package com.dev.cinema.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;

public class OrderRequestDto {
    @NotNull
    private LocalDateTime time;
    @NotNull
    private List<TicketRequestDto> ticketsRequestDto;
    @NotNull
    private Long userId;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<TicketRequestDto> getTicketsRequestDto() {
        return ticketsRequestDto;
    }

    public void setTicketsRequestDto(List<TicketRequestDto> ticketsRequestDto) {
        this.ticketsRequestDto = ticketsRequestDto;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
