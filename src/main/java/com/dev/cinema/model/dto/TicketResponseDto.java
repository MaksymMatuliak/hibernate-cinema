package com.dev.cinema.model.dto;

import lombok.Data;

@Data
public class TicketResponseDto {
    private Long ticketId;
    private UserResponseDto userResponseDto;
    private MovieSessionResponseDto movieSessionResponseDto;
}
