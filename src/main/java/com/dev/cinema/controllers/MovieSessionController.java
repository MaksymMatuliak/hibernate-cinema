package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.util.MovieSessionConvertUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final MovieSessionConvertUtil movieSessionConvertUtil;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieService movieService,
                                  CinemaHallService cinemaHallService,
                                  MovieSessionConvertUtil movieSessionConvertUtil) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionConvertUtil = movieSessionConvertUtil;
    }

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionConvertUtil.requestDtoToEntity(movieSessionRequestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableMovieSessions(
            @RequestParam Long movieSessionId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return movieSessionService.findAvailableSessions(movieSessionId, date)
                .stream()
                .map(movieSessionConvertUtil::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
