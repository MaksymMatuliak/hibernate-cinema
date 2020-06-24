package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieRequestDto {
    @NotNull
    @Size(min = 2)
    private String title;
    @NotNull
    @Size(min = 10)
    private String description;
}
