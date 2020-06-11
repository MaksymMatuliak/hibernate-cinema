package com.dev.cinema.controllers;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.util.ConverterUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    private final MovieService movieService;
    private final ConverterUtil converterUtil;

    public MovieController(MovieService movieService, ConverterUtil converterUtil) {
        this.movieService = movieService;
        this.converterUtil = converterUtil;
    }

    @PostMapping("/movies")
    public void addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(converterUtil.convertMovieRequestDtoIntoMovie(movieRequestDto));
    }

    @GetMapping("/movies")
    public List<MovieResponseDto> getMovies() {
        List<MovieResponseDto> moviesResponseDto = new ArrayList<>();
        for (Movie movie : movieService.getAll()) {
            moviesResponseDto.add(converterUtil.convertMovieIntoMovieResponseDto(movie));
        }
        return moviesResponseDto;
    }
}
