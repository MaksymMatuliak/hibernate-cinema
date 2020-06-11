package com.dev.cinema.controllers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.util.ConverterUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final ConverterUtil converterUtil;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieService movieService,
                                  CinemaHallService cinemaHallService,
                                  ConverterUtil converterUtil) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.converterUtil = converterUtil;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(converterUtil.convertMovieSessionRequestDtoIntoMovieSession(
                movieSessionRequestDto));
    }

    @GetMapping("/movie-sessions/available")
    public List<MovieSessionResponseDto> getAvailableMovieSessions(
            @RequestParam Long movieSessionId, @RequestParam String date) {
        List<MovieSessionResponseDto> movieSessionsResponseDto = new ArrayList<>();
        for (MovieSession movieSession : movieSessionService.findAvailableSessions(
                movieSessionId, LocalDate.parse(date, DateTimeFormatter.ofPattern("MM.dd.yyyy")))) {
            movieSessionsResponseDto.add(
                    converterUtil.convertMovieSessionIntoMovieSessionResponseDto(movieSession));
        }
        return movieSessionsResponseDto;
    }
}
