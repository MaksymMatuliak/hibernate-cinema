package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long movieSessionId;
}
