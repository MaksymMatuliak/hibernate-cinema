package com.dev.cinema.model.dto;

import lombok.Data;

@Data
public class MovieResponseDto {
    private Long movieId;
    private String title;
    private String description;
}
