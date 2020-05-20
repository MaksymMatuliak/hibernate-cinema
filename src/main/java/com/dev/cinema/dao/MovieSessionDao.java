package com.dev.cinema.dao;

import com.dev.cinema.model.MovieSession;
import java.util.List;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> getAll();
}
