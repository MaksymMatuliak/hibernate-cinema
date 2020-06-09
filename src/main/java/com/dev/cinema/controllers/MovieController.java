package com.dev.cinema.controllers;

import com.dev.cinema.model.Dto.MovieDto;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MovieController {
    private MovieService movieService;

    @PostMapping("/movies")
    public void addMovie(String title, String description) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movieService.add(movie);
    }

    @ResponseBody
    @GetMapping("/movies")
    public List<MovieDto> getMovies() {
        List<MovieDto> moviesDto = new ArrayList<>();
        for (Movie movie : movieService.getAll()) {
            moviesDto.add(convertMovieIntoMovieDto(movie));
        }
        return moviesDto;
    }

    private MovieDto convertMovieIntoMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieId(movie.getMovieId());
        movieDto.setDescription(movie.getDescription());
        movieDto.setTitle(movie.getTitle());
        return movieDto;
    }
}
