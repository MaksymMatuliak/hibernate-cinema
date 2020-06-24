package com.dev.cinema.model.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long movieSessionId;
    private LocalDateTime time;
    private String movieTitle;
    private Long cinemaHallId;
}
