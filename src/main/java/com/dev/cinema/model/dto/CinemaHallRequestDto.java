package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @NotNull
    @Size(min = 2)
    private String description;
    private int capacity;
}
