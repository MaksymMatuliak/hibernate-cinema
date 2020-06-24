package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieSessionRequestIdDto {
    @NotNull
    private Long movieSessionId;
}
