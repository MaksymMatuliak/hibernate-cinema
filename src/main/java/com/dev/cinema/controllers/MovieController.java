package com.dev.cinema.controllers;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.util.MovieConvertUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    private final MovieService movieService;
    private final MovieConvertUtil movieConvertUtil;

    public MovieController(MovieService movieService, MovieConvertUtil movieConvertUtil) {
        this.movieService = movieService;
        this.movieConvertUtil = movieConvertUtil;
    }

    @PostMapping("/movies")
    public void addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieConvertUtil.convertMovieRequestDtoIntoMovie(movieRequestDto));
    }

    @GetMapping("/movies")
    public List<MovieResponseDto> getMovies() {
        List<MovieResponseDto> moviesResponseDto = new ArrayList<>();
        for (Movie movie : movieService.getAll()) {
            moviesResponseDto.add(movieConvertUtil.convertMovieIntoMovieResponseDto(movie));
        }
        return moviesResponseDto;
    }
}
