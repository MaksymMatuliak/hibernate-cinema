package com.dev.cinema.model.Dto;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import java.time.LocalDateTime;

public class MovieSessionDto {
    private Long movieSessionId;
    private LocalDateTime time;
    private Movie movie;
    private CinemaHall cinemaHall;

}
