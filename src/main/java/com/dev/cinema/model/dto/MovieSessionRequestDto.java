package com.dev.cinema.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class MovieSessionRequestDto {
    @NotNull
    private LocalDateTime time;
    @NotNull
    private Long movieId;
    @NotNull
    private Long cinemaHallId;

    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getTime() {
        return time;
    }
}
