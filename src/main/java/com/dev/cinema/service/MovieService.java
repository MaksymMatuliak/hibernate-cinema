package com.dev.cinema.service;

import com.dev.cinema.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();

    Optional<Movie> getById(Long movieId);
}
