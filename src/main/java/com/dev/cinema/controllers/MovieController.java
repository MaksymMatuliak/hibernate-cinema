package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.util.MovieConvertUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieConvertUtil movieConvertUtil;

    public MovieController(MovieService movieService, MovieConvertUtil movieConvertUtil) {
        this.movieService = movieService;
        this.movieConvertUtil = movieConvertUtil;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieConvertUtil.requestDtoToEntity(movieRequestDto));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MovieResponseDto> getMovies() {
        return movieService.getAll()
                .stream()
                .map(movieConvertUtil::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
