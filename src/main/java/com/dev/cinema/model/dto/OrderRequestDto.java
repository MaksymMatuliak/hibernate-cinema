package com.dev.cinema.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {
    @NotNull
    private LocalDateTime time;
    @NotNull
    private List<TicketRequestDto> ticketsRequestDto;
    @NotNull
    private Long userId;
}
