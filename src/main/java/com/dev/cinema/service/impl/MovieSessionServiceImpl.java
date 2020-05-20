package com.dev.cinema.service.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.getAll()
                .stream()
                .filter(f -> f.getMovie().getMovieId().equals(movieId)
                        && f.getTime().getYear() == date.getYear()
                        && f.getTime().getMonth().equals(date.getMonth())
                        && f.getTime().getDayOfMonth() == date.getDayOfMonth())
                .collect(Collectors.toList());
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
