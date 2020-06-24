package com.dev.cinema.model.dto;

import lombok.Data;

@Data
public class CinemaHallResponseDto {
    private Long cinemaId;
    private String description;
    private int capacity;
}
