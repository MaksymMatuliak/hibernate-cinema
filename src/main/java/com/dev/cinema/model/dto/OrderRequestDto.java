package com.dev.cinema.model.dto;

import java.util.List;

public class OrderRequestDto {
    private String time;
    private List<TicketRequestDto> ticketsRequestDto;
    private String userEmail;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<TicketRequestDto> getTicketsRequestDto() {
        return ticketsRequestDto;
    }

    public void setTicketsRequestDto(List<TicketRequestDto> ticketsRequestDto) {
        this.ticketsRequestDto = ticketsRequestDto;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
